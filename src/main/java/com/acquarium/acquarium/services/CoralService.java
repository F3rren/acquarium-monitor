package com.acquarium.acquarium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquarium.acquarium.models.Coral;
import com.acquarium.acquarium.repository.ICoralRepository;

import com.acquarium.acquarium.exceptions.ResourceNotFoundException;

@Service
public class CoralService {

    @Autowired
    private ICoralRepository coralRepository;

    public List<Coral> getAllCorals() {
        return coralRepository.findAllSortedByName();
    }

    public Coral getCoralById(Long id) {
        return coralRepository.findById(id.intValue())
            .orElseThrow(() -> new ResourceNotFoundException("Corallo non trovato con ID: " + id));
    }
}
