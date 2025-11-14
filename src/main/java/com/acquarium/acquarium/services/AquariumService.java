package com.acquarium.acquarium.services;

import java.util.List;
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
    
    public void deleteAquarium(Long id) {
        aquariumRepository.deleteById(id);
    }
    
    
}
