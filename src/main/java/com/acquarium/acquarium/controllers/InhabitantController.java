package com.acquarium.acquarium.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acquarium.acquarium.dtos.InhabitantDetailsDTO;
import com.acquarium.acquarium.models.Inhabitant;
import com.acquarium.acquarium.services.InhabitantService;

@RestController
@RequestMapping("api/aquariums")
@CrossOrigin(origins = "*")

public class InhabitantController {

    @Autowired
    private InhabitantService inhabitantService;

    @GetMapping("/{id}/inhabitants")
    public ResponseEntity<?> getAquariumInhabitants(@PathVariable Long id) {
        List<InhabitantDetailsDTO> inhabitants = inhabitantService.getInhabitantsByAquariumId(id);

        Map<String, Object> response = Map.of(
                "success", true,
                "message", "Abitanti recuperati con successo",
                "data", inhabitants,
                "metadata", Map.of(
                        "aquariumId", id,
                        "totalCount", inhabitants.size(),
                        "fishCount", inhabitants.stream().filter(i -> "fish".equals(i.getType())).count(),
                        "coralCount", inhabitants.stream().filter(i -> "coral".equals(i.getType())).count()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/inhabitants")
    public ResponseEntity<?> addInhabitantToAquarium(
            @PathVariable Long id,
            @RequestBody Inhabitant inhabitant) {
        
        try {
            inhabitant.setAquariumId(id);
            Inhabitant saved = inhabitantService.addInhabitant(inhabitant);
            
            Map<String, Object> response = Map.of(
                "success", true,
                "message", "Abitante aggiunto con successo",
                "data", saved
            );
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, Object> response = Map.of(
                "success", false,
                "message", "Errore durante l'aggiunta dell'abitante: " + e.getMessage()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{aquariumId}/inhabitants/{inhabitantId}")
    public ResponseEntity<?> removeInhabitantFromAquarium(
            @PathVariable Long aquariumId,
            @PathVariable Long inhabitantId) {
        
        inhabitantService.removeInhabitant(inhabitantId);
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Abitante rimosso con successo"
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   
}
