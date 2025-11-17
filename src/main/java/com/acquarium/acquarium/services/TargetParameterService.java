package com.acquarium.acquarium.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acquarium.acquarium.models.TargetParameter;
import com.acquarium.acquarium.repository.ITargetParameterRepository;

@Service
public class TargetParameterService {
    
    @Autowired
    private ITargetParameterRepository targetParameterRepository;
    
    public TargetParameter getTargetParameters(Long aquariumId) {
        return targetParameterRepository.findByAquariumId(aquariumId).orElse(null);
    }
    
    public TargetParameter saveTargetParameters(Long aquariumId, TargetParameter targetParameter) {
        Optional<TargetParameter> existing = targetParameterRepository.findByAquariumId(aquariumId);
        
        if (existing.isPresent()) {
            // Aggiorna i parametri esistenti
            TargetParameter target = existing.get();
            if (targetParameter.getTemperature() != null) target.setTemperature(targetParameter.getTemperature());
            if (targetParameter.getPh() != null) target.setPh(targetParameter.getPh());
            if (targetParameter.getSalinity() != null) target.setSalinity(targetParameter.getSalinity());
            if (targetParameter.getOrp() != null) target.setOrp(targetParameter.getOrp());
            
            return targetParameterRepository.save(target);
        } else {
            // Crea nuovi parametri target
            targetParameter.setAquariumId(aquariumId);
            return targetParameterRepository.save(targetParameter);
        }
    }
}
