// InhabitantDetailsDTO.java
package com.acquarium.acquarium.dtos;

import java.time.LocalDateTime;

public class InhabitantDetailsDTO {
    private Long id;
    private String type; // "fish" o "coral"
    private String commonName;
    private String scientificName;
    private Integer quantity;
    private LocalDateTime addedDate;
    private Object details; // Fish o Coral completo
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCommonName() {
        return commonName;
    }
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
    public String getScientificName() {
        return scientificName;
    }
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
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
    public Object getDetails() {
        return details;
    }
    public void setDetails(Object details) {
        this.details = details;
    }
    
    // Constructor, getters, setters
}