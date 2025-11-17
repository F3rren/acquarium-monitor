// AquariumInhabitantService.java
package com.acquarium.acquarium.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acquarium.acquarium.dtos.InhabitantDetailsDTO;
import com.acquarium.acquarium.models.*;
import com.acquarium.acquarium.repository.*;

@Service
public class InhabitantService {
    
    @Autowired
    private IInhabitantRepository inhabitantRepository;
    
    @Autowired
    private FishService fishService;
    
    @Autowired
    private CoralService coralService;
    
    public List<InhabitantDetailsDTO> getInhabitantsByAquariumId(Long aquariumId) {
        List<Inhabitant> relations = inhabitantRepository.findByAquariumId(aquariumId);
        List<InhabitantDetailsDTO> result = new ArrayList<>();
        
        for (Inhabitant relation : relations) {
            InhabitantDetailsDTO dto = new InhabitantDetailsDTO();
            dto.setId(relation.getId());
            dto.setType(relation.getInhabitantType());
            dto.setQuantity(relation.getQuantity());
            dto.setAddedDate(relation.getAddedDate());
            
            if ("fish".equals(relation.getInhabitantType())) {
                Fish fish = fishService.getFishById(relation.getInhabitantId());
                if (fish != null) {
                    dto.setCommonName(fish.getCommonName());
                    dto.setScientificName(fish.getScientificName());
                    dto.setDetails(fish);
                }
            } else if ("coral".equals(relation.getInhabitantType())) {
                Coral coral = coralService.getCoralById(relation.getInhabitantId());
                if (coral != null) {
                    dto.setCommonName(coral.getCommonName());
                    dto.setScientificName(coral.getScientificName());
                    dto.setDetails(coral);
                }
            }
            
            result.add(dto);
        }
        
        return result;
    }
    
    public Inhabitant addInhabitant(Inhabitant inhabitant) {
        // Validazione
        if (inhabitant.getInhabitantType() == null || inhabitant.getInhabitantId() == null) {
            throw new IllegalArgumentException("inhabitantType e inhabitantId sono obbligatori");
        }
        
        // Verifica che il pesce o corallo esista
        if ("fish".equals(inhabitant.getInhabitantType())) {
            Fish fish = fishService.getFishById(inhabitant.getInhabitantId());
            if (fish == null) {
                throw new IllegalArgumentException("Pesce con ID " + inhabitant.getInhabitantId() + " non trovato");
            }
        } else if ("coral".equals(inhabitant.getInhabitantType())) {
            Coral coral = coralService.getCoralById(inhabitant.getInhabitantId());
            if (coral == null) {
                throw new IllegalArgumentException("Corallo con ID " + inhabitant.getInhabitantId() + " non trovato");
            }
        } else {
            throw new IllegalArgumentException("inhabitantType deve essere 'fish' o 'coral'");
        }
        
        // Imposta quantity a 1 se null
        if (inhabitant.getQuantity() == null) {
            inhabitant.setQuantity(1);
        }
        
        return inhabitantRepository.save(inhabitant);
    }
    
    public void removeInhabitant(Long id) {
        inhabitantRepository.deleteById(id);
    }
}