package com.acquarium.acquarium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquarium.acquarium.models.Fish;
import com.acquarium.acquarium.repository.IFishRepository;

@Service
public class FishService {
    
    @Autowired
    private IFishRepository fishRepository;
    
    public List<Fish> getAllFishs() {
        return fishRepository.findAll();
    }

    public Fish getFishById(Long id) {
        return fishRepository.findById(id.intValue()).orElse(null);
    }
}
