package com.acquarium.acquarium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.acquarium.acquarium.models.Aquarium;

public interface IAquariumRepository extends JpaRepository<Aquarium, Long> {
    
    @Query("SELECT a FROM Aquarium a")
    List<Aquarium> findAll();

    @Query("Insert into Aquarium (name, volume, type, createdAt, description, imageUrl) VALUES (?1, ?2, ?3, ?4, ?5, ?6)")
    void createAquarium(Aquarium aquarium);

}
