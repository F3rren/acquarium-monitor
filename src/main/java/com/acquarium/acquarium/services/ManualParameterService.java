package com.acquarium.acquarium.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acquarium.acquarium.models.ManualParameter;
import com.acquarium.acquarium.repository.IManualParameterRepository;

import com.acquarium.acquarium.exceptions.ResourceNotFoundException;

@Service
public class ManualParameterService {
    
    @Autowired
    private IManualParameterRepository manualParameterRepository;
    
    public ManualParameter saveManualParameter(Long aquariumId, ManualParameter parameter) {
        parameter.setAquariumId(aquariumId);
        return manualParameterRepository.save(parameter);
    }
    
    public ManualParameter getLatestManualParameter(Long aquariumId) {
        ManualParameter parameter = manualParameterRepository.findFirstByAquariumIdOrderByMeasuredAtDesc(aquariumId);
        if (parameter == null) {
            throw new ResourceNotFoundException("Nessun parametro manuale trovato per l'acquario con ID: " + aquariumId);
        }
        return parameter;
    }
    
    public List<ManualParameter> getAllManualParameters(Long aquariumId) {
        return manualParameterRepository.findByAquariumIdOrderByMeasuredAtDesc(aquariumId);
    }
    
    public List<ManualParameter> getManualParametersHistory(Long aquariumId, LocalDateTime from, LocalDateTime to) {
        return manualParameterRepository.findByAquariumIdAndMeasuredAtBetweenOrderByMeasuredAtDesc(
            aquariumId, from, to
        );
    }
}
