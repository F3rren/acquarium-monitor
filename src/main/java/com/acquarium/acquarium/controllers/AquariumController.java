package com.acquarium.acquarium.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.models.Parameter;
import com.acquarium.acquarium.models.ManualParameter;
import com.acquarium.acquarium.models.TargetParameter;
import com.acquarium.acquarium.services.AquariumService;
import com.acquarium.acquarium.services.ParameterService;
import com.acquarium.acquarium.services.ManualParameterService;
import com.acquarium.acquarium.services.TargetParameterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/aquariums")
@Tag(name = "Aquarium", description = "API for managing aquariums")
public class AquariumController {

    @Autowired
    private AquariumService aquariumService;
    
    @Autowired
    private ParameterService parameterService;
    
    @Autowired
    private ManualParameterService manualParameterService;
    
    @Autowired
    private TargetParameterService targetParameterService;

    @GetMapping
    @Operation(summary = "Get all aquariums", description = "Retrieve details of all aquariums")
    public ResponseEntity<?> getAllAquariums() {
        List<Aquarium> aquariums = aquariumService.getAllAquariums();

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Acquari recuperati con successo",
                "data", aquariums);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get aquarium by ID", description = "Retrieve details of a specific aquarium")
    public ResponseEntity<?> getAquariumById(@PathVariable Long id) {
        Aquarium aquarium = aquariumService.getAquariumById(id);

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Acquario recuperato con successo",
                "data", aquarium);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Endpoint per ricevere dati DAI sensori
    @PostMapping("/{id}/parameters")
    @Operation(summary = "Add parameters for a specific aquarium", description = "Receive and save sensor data for a specific aquarium")
    public ResponseEntity<?> addParameters(@PathVariable Long id, @RequestBody Parameter parameter) {
        try {
            Parameter saved = parameterService.saveParameter(id, parameter);
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Parametri salvati con successo",
                "data", saved
            );
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante il salvataggio dei parametri: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/parameters")
    @Operation(summary = "Get parameters for a specific aquarium", description = "Retrieve sensor data for a specific aquarium with optional limit and period filters")
    public ResponseEntity<?> getAquariumParameters(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false) String period // "day", "week", "month"
    ) {
        List<Parameter> parameters;
        
        if (period != null) {
            parameters = parameterService.getParametersByPeriod(id, period);
        } else {
            parameters = parameterService.getParametersByAquariumId(id, limit);
        }

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Parametri recuperati con successo",
                "data", parameters,
                "metadata", Map.of(
                        "aquariumId", id,
                        "count", parameters.size(),
                        "limit", limit));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint per ultimo valore (utile per dashboard)
    @GetMapping("/{id}/parameters/latest")
    @Operation(summary = "Get latest parameters for a specific aquarium", description = "Retrieve the most recent sensor data for a specific aquarium")
    public ResponseEntity<?> getLatestParameters(@PathVariable Long id) {
        Parameter latest = parameterService.getLatestParameter(id);
        
        if (latest == null) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Nessun parametro trovato per questo acquario"
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> data = Map.of(
                "temperature", latest.getTemperature(),
                "ph", latest.getPh(),
                "salinity", latest.getSalinity(),
                "orp", latest.getOrp(),
                "timestamp", latest.getMeasuredAt(),
                "status", Map.of(
                        "temperature", latest.getTemperatureStatus(),
                        "ph", latest.getPhStatus(),
                        "salinity", latest.getSalinityStatus(),
                        "orp", latest.getOrpStatus()));

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Ultimi parametri recuperati con successo",
                "data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Endpoint per storico con filtri personalizzati per grafici
    @GetMapping("/{id}/parameters/history")
    @Operation(summary = "Get parameters history for a specific aquarium", description = "Retrieve historical sensor data for a specific aquarium with optional date range and parameter filters")
    public ResponseEntity<?> getParametersHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @RequestParam(required = false) String param) {
        
        try {
            LocalDateTime fromDate;
            LocalDateTime toDate;
            
            // Default: ultima settimana
            if (from == null || from.isEmpty()) {
                fromDate = LocalDateTime.now().minusWeeks(1);
            } else {
                fromDate = LocalDateTime.parse(from);
            }
            
            if (to == null || to.isEmpty()) {
                toDate = LocalDateTime.now();
            } else {
                toDate = LocalDateTime.parse(to);
            }
            
            List<Parameter> parameters = parameterService.getParametersHistory(id, fromDate, toDate);
            
            // Se è specificato un parametro, filtra solo quel valore
            if (param != null && !param.isEmpty()) {
                List<Map<String, Object>> filtered = parameters.stream()
                    .map(p -> {
                        Object value = switch(param.toLowerCase()) {
                            case "temperature" -> p.getTemperature();
                            case "ph" -> p.getPh();
                            case "salinity" -> p.getSalinity();
                            case "orp" -> p.getOrp();
                            default -> null;
                        };
                        
                        return Map.of(
                            "timestamp", p.getMeasuredAt(),
                            "value", value != null ? value : 0
                        );
                    })
                    .collect(Collectors.toList());
                
                Map<String, Object> response = Map.of(
                    "success", true,
                    "message", "Storico parametro recuperato con successo",
                    "data", filtered,
                    "metadata", Map.of(
                        "aquariumId", id,
                        "parameter", param,
                        "from", fromDate,
                        "to", toDate,
                        "count", filtered.size()
                    )
                );
                
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            
            // Altrimenti ritorna tutti i parametri
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Storico parametri recuperato con successo",
                "data", parameters,
                "metadata", Map.of(
                    "aquariumId", id,
                    "from", fromDate,
                    "to", toDate,
                    "count", parameters.size()
                )
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante il recupero dello storico: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint per salvare parametri manuali
    @PostMapping("/{id}/parameters/manual")
    @Operation(summary = "Save manual parameters for a specific aquarium", description = "Receive and save manual parameter data for a specific aquarium")
    public ResponseEntity<?> saveManualParameters(
            @PathVariable Long id,
            @RequestBody ManualParameter manualParameter) {
        
        try {
            ManualParameter saved = manualParameterService.saveManualParameter(id, manualParameter);
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Parametri manuali salvati con successo",
                "data", saved
            );
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante il salvataggio dei parametri manuali: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Endpoint per recuperare ultimi parametri manuali
    @GetMapping("/{id}/parameters/manual")
    @Operation(summary = "Get latest manual parameters for a specific aquarium", description = "Retrieve the most recent manual parameter data for a specific aquarium")
    public ResponseEntity<?> getLatestManualParameters(@PathVariable Long id) {
        ManualParameter latest = manualParameterService.getLatestManualParameter(id);
        
        if (latest == null) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Nessun parametro manuale trovato per questo acquario"
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> data = Map.of(
            "calcium", latest.getCalcium() != null ? latest.getCalcium() : 0,
            "magnesium", latest.getMagnesium() != null ? latest.getMagnesium() : 0,
            "kh", latest.getKh() != null ? latest.getKh() : 0,
            "nitrate", latest.getNitrate() != null ? latest.getNitrate() : 0,
            "phosphate", latest.getPhosphate() != null ? latest.getPhosphate() : 0,
            "measuredAt", latest.getMeasuredAt(),
            "status", Map.of(
                "calcium", latest.getCalciumStatus(),
                "magnesium", latest.getMagnesiumStatus(),
                "kh", latest.getKhStatus(),
                "nitrate", latest.getNitrateStatus(),
                "phosphate", latest.getPhosphateStatus()
            )
        );
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Ultimi parametri manuali recuperati con successo",
            "data", data
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Endpoint per storico parametri manuali
    @GetMapping("/{id}/parameters/manual/history")
    @Operation(summary = "Get manual parameters history for a specific aquarium", description = "Retrieve historical manual parameter data for a specific aquarium with optional date range")
    public ResponseEntity<?> getManualParametersHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        
        try {
            List<ManualParameter> parameters;
            
            if (from != null && to != null) {
                LocalDateTime fromDate = LocalDateTime.parse(from);
                LocalDateTime toDate = LocalDateTime.parse(to);
                parameters = manualParameterService.getManualParametersHistory(id, fromDate, toDate);
            } else {
                parameters = manualParameterService.getAllManualParameters(id);
            }
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Storico parametri manuali recuperato con successo",
                "data", parameters,
                "metadata", Map.of(
                    "aquariumId", id,
                    "count", parameters.size()
                )
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
            
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante il recupero dello storico: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    // Endpoint per recuperare parametri target (valori ideali personalizzati)
    @GetMapping("/{id}/settings/targets")
    @Operation(summary = "Get target parameters for a specific aquarium", description = "Retrieve target parameter settings for a specific aquarium, returning default values if none are set")
    public ResponseEntity<?> getTargetParameters(@PathVariable Long id) {
        TargetParameter targets = targetParameterService.getTargetParameters(id);
        
        if (targets == null) {
            // Ritorna valori default se non esistono parametri target personalizzati
            Map<String, Object> defaultTargets = Map.of(
                "temperature", 25.0,
                "ph", 8.2,
                "salinity", 1024.0,
                "orp", 360.0
            );
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Parametri target di default",
                "data", defaultTargets
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        Map<String, Object> data = Map.of(
            "temperature", targets.getTemperature() != null ? targets.getTemperature() : 25.0,
            "ph", targets.getPh() != null ? targets.getPh() : 8.2,
            "salinity", targets.getSalinity() != null ? targets.getSalinity() : 1024.0,
            "orp", targets.getOrp() != null ? targets.getOrp() : 360.0
        );
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Parametri target recuperati con successo",
            "data", data
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    // Endpoint per salvare/aggiornare parametri target
    @PostMapping("/{id}/settings/targets")
    @Operation(summary = "Save target parameters for a specific aquarium", description = "Receive and save target parameter settings for a specific aquarium")
    public ResponseEntity<?> saveTargetParameters(
            @PathVariable Long id,
            @RequestBody TargetParameter targetParameter) {
        
        try {
            TargetParameter saved = targetParameterService.saveTargetParameters(id, targetParameter);
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Parametri target salvati con successo",
                "data", saved
            );
            
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante il salvataggio dei parametri target: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new aquarium", description = "Receive and save a new aquarium")
    public ResponseEntity<?> createAquarium(@RequestBody Aquarium aquarium) {
        Aquarium savedAquarium = aquariumService.createAquarium(aquarium);

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Acquario creato con successo",
                "data", savedAquarium);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAquarium(@PathVariable Long id, @RequestBody Aquarium aquarium) {
        try {
            Aquarium updatedAquarium = aquariumService.updateAquarium(id, aquarium);

            Map<String, Object> response = Map.of(
                    "success", true,
                    "message", "Acquario modificato con successo",
                    "data", updatedAquarium);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> response = Map.of(
                    "success", false,
                    "message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a specific aquarium", description = "Delete a specific aquarium by its ID")
    public ResponseEntity<?> deleteAquarium(@PathVariable Long id) {
        aquariumService.deleteAquarium(id);

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Acquario eliminato con successo");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}