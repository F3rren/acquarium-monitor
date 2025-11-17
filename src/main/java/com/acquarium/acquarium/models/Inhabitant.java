// AquariumInhabitant.java
package com.acquarium.acquarium.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "aquarium_inhabitants")
public class Inhabitant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "aquarium_id", nullable = false)
    private Long aquariumId;
    
    @Column(name = "inhabitant_type", nullable = false)
    private String inhabitantType; // "fish" o "coral"
    
    @Column(name = "inhabitant_id", nullable = false)
    private Long inhabitantId;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @CreationTimestamp
    @Column(name = "added_date")
    private LocalDateTime addedDate;
    
    @Column(name = "notes")
    private String notes;
    
    public Inhabitant() {}
    
    public Inhabitant(Long id, Long aquariumId, String inhabitantType, Long inhabitantId, Integer quantity,
            LocalDateTime addedDate, String notes) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.inhabitantType = inhabitantType;
        this.inhabitantId = inhabitantId;
        this.quantity = quantity;
        this.addedDate = addedDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAquariumId() {
        return aquariumId;
    }

    public void setAquariumId(Long aquariumId) {
        this.aquariumId = aquariumId;
    }

    public String getInhabitantType() {
        return inhabitantType;
    }

    public void setInhabitantType(String inhabitantType) {
        this.inhabitantType = inhabitantType;
    }

    public Long getInhabitantId() {
        return inhabitantId;
    }

    public void setInhabitantId(Long inhabitantId) {
        this.inhabitantId = inhabitantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}