package com.acquarium.acquarium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.repository.IAquariumRepository;
import com.acquarium.acquarium.exceptions.ResourceNotFoundException;

@Service
public class AquariumService {
    
    @Autowired
    private IAquariumRepository aquariumRepository;

    public Aquarium createAquarium(Aquarium aquarium) {
        return aquariumRepository.save(aquarium);
    }

    public List<Aquarium> getAllAquariums() {
        List<Aquarium> aquariums = aquariumRepository.findAll();
        if (aquariums.isEmpty()) {
            throw new ResourceNotFoundException("Nessun acquario trovato");
        }
        return aquariums;
    }
    
    public Aquarium getAquariumById(Long id) {
        return aquariumRepository.findById(id)
                .orElseThrow(() -> new com.acquarium.acquarium.exceptions.ResourceNotFoundException("Acquario non trovato con ID: " + id));
    }

    public Aquarium updateAquarium(Long id, Aquarium aquarium) {
        // Verifica che l'acquario esista
        if (!aquariumRepository.existsById(id)) {
            throw new com.acquarium.acquarium.exceptions.ResourceNotFoundException("Acquario non trovato con ID: " + id);
        }
        aquarium.setId(id.intValue());  // Imposta l'ID per l'update
        return aquariumRepository.save(aquarium);
    }
    
    public void deleteAquarium(Long id) {
        if (!aquariumRepository.existsById(id)) {
            throw new com.acquarium.acquarium.exceptions.ResourceNotFoundException("Acquario non trovato con ID: " + id);
        }
        aquariumRepository.deleteById(id);
    }
}