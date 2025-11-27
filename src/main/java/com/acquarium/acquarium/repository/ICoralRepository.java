package com.acquarium.acquarium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acquarium.acquarium.models.Coral;

public interface ICoralRepository extends JpaRepository<Coral, Integer> {
    
    @Query("SELECT c FROM Coral c ORDER BY c.commonName ASC")
    public List<Coral> findAllSortedByName();
}
