package com.acquarium.acquarium.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.ManualParameter;

public interface IManualParameterRepository extends JpaRepository<ManualParameter, Long> {
    
    List<ManualParameter> findByAquariumIdOrderByMeasuredAtDesc(Long aquariumId);
    
    ManualParameter findFirstByAquariumIdOrderByMeasuredAtDesc(Long aquariumId);
    
    List<ManualParameter> findByAquariumIdAndMeasuredAtBetweenOrderByMeasuredAtDesc(
        Long aquariumId, 
        LocalDateTime start, 
        LocalDateTime end
    );
}
