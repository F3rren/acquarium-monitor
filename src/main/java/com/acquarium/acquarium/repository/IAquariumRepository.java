package com.acquarium.acquarium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.Aquarium;

public interface IAquariumRepository extends JpaRepository<Aquarium, Long> {

}
