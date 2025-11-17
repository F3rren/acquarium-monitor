package com.acquarium.acquarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.Coral;

public interface ICoralRepository extends JpaRepository<Coral, Integer> {
    
}
