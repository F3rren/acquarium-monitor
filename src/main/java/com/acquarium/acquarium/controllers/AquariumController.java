package com.acquarium.acquarium.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.services.AquariumService;

@RestController
@RequestMapping("api/aquariums")
@CrossOrigin(origins = "*") 
public class AquariumController {
    
    @Autowired
    private AquariumService aquariumService;

    @GetMapping
    public ResponseEntity<?> getAllAquariums() {
        List<Aquarium> aquariums = aquariumService.getAllAquariums();
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Acquari recuperati con successo",
            "data", aquariums
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAquarium(@RequestBody Aquarium aquarium) {
        Aquarium savedAquarium = aquariumService.createAquarium(aquarium);
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Acquario creato con successo",
            "data", savedAquarium
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAquarium(@PathVariable Long id) {
        aquariumService.deleteAquarium(id);
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Acquario eliminato con successo"
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}