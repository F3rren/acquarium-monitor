package com.acquarium.acquarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.Fish;

public interface IFishRepository extends JpaRepository<Fish, Integer> {
    
}
