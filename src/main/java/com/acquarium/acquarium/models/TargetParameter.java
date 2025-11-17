package com.acquarium.acquarium.models;

import jakarta.persistence.*;

@Entity
@Table(name = "target_parameters")
public class TargetParameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "aquarium_id", nullable = false, unique = true)
    private Long aquariumId;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "ph")
    private Double ph;
    
    @Column(name = "salinity")
    private Double salinity;
    
    @Column(name = "orp")
    private Double orp;
    
    public TargetParameter() {}

    public TargetParameter(Long id, Long aquariumId, Double temperature, Double ph, Double salinity, Double orp) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.temperature = temperature;
        this.ph = ph;
        this.salinity = salinity;
        this.orp = orp;
    }

    // Getters e Setters
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
    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public Double getPh() {
        return ph;
    }
    public void setPh(Double ph) {
        this.ph = ph;
    }
    public Double getSalinity() {
        return salinity;
    }
    public void setSalinity(Double salinity) {
        this.salinity = salinity;
    }
    public Double getOrp() {
        return orp;
    }
    public void setOrp(Double orp) {
        this.orp = orp;
    }
}
