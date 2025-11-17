package com.acquarium.acquarium.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquarium.acquarium.models.Aquarium;
import com.acquarium.acquarium.repository.IAquariumRepository;

@Service
public class AquariumService {
    
    @Autowired
    private IAquariumRepository aquariumRepository;

    public Aquarium createAquarium(Aquarium aquarium) {
        return aquariumRepository.save(aquarium);
    }

    public List<Aquarium> getAllAquariums() {
        return aquariumRepository.findAll();
    }
    
    public Aquarium getAquariumById(Long id) {
        return aquariumRepository.findById(id).orElse(null);
    }

    public Aquarium updateAquarium(Long id, Aquarium aquarium) {
        // Verifica che l'acquario esista
        Optional<Aquarium> existing = aquariumRepository.findById(id);
        if (existing.isPresent()) {
            aquarium.setId(id.intValue());  // Imposta l'ID per l'update
            return aquariumRepository.save(aquarium);  // save() fa automaticamente l'update se l'ID esiste
        }
        throw new RuntimeException("Acquario non trovato con ID: " + id);
    }
    
    public void deleteAquarium(Long id) {
        aquariumRepository.deleteById(id);
    }
}