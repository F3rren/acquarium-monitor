package com.acquarium.acquarium.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acquarium.acquarium.models.Fish;
import com.acquarium.acquarium.services.FishService;

@RestController
@RequestMapping("api/species/fish")
public class FishController {
    
    @Autowired
    private FishService fishService;

    @GetMapping
    public ResponseEntity<?> getAllFishs() {
        List<Fish> fishs = fishService.getAllFishs();
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Pesci recuperati con successo",
            "data", fishs
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        
        Map<String, Object> response = Map.of(
            "success", true,
            "message", "Pesce recuperato con successo",
            "data", fish
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
