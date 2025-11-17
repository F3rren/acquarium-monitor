package com.acquarium.acquarium.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acquarium.acquarium.models.Parameter;
import com.acquarium.acquarium.repository.IParameterRepository;

@Service
public class ParameterService {
    
    @Autowired
    private IParameterRepository parameterRepository;
    
    public Parameter saveParameter(Long aquariumId, Parameter parameter) {
        parameter.setAquariumId(aquariumId);
        return parameterRepository.save(parameter);
    }
    
    public List<Parameter> getParametersByAquariumId(Long aquariumId, Integer limit) {
        if (limit != null && limit <= 50) {
            return parameterRepository.findByAquariumIdOrderByMeasuredAtDesc(aquariumId)
                .stream()
                .limit(limit)
                .toList();
        }
        return parameterRepository.findByAquariumIdOrderByMeasuredAtDesc(aquariumId);
    }
    
    public Parameter getLatestParameter(Long aquariumId) {
        return parameterRepository.findFirstByAquariumIdOrderByMeasuredAtDesc(aquariumId);
    }
    
    public List<Parameter> getParametersByPeriod(Long aquariumId, String period) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start;
        
        switch (period != null ? period : "day") {
            case "week":
                start = end.minusWeeks(1);
                break;
            case "month":
                start = end.minusMonths(1);
                break;
            default:
                start = end.minusDays(1);
        }
        
        return parameterRepository.findByAquariumIdAndMeasuredAtBetweenOrderByMeasuredAtDesc(
            aquariumId, start, end
        );
    }
    
    public List<Parameter> getParametersHistory(Long aquariumId, LocalDateTime from, LocalDateTime to) {
        return parameterRepository.findByAquariumIdAndMeasuredAtBetweenOrderByMeasuredAtDesc(
            aquariumId, from, to
        );
    }
}
