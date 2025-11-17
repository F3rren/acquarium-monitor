package com.acquarium.acquarium.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.TargetParameter;

public interface ITargetParameterRepository extends JpaRepository<TargetParameter, Long> {
    
    Optional<TargetParameter> findByAquariumId(Long aquariumId);
}
