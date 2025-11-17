package com.acquarium.acquarium.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acquarium.acquarium.models.Inhabitant;

public interface IInhabitantRepository extends JpaRepository<Inhabitant, Long>{

    List<Inhabitant> findByAquariumId(Long aquariumId);
    List<Inhabitant> findByAquariumIdAndInhabitantType(Long aquariumId, String inhabitantType);
}
