package com.acquarium.acquarium.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "water_parameters")
public class Parameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "aquarium_id", nullable = false)
    private Long aquariumId;
    
    @Column(name = "temperature", nullable = false)
    private Double temperature;
    
    @Column(name = "ph", nullable = false)
    private Double ph;
    
    @Column(name = "salinity", nullable = false)
    private Integer salinity;
    
    @Column(name = "orp", nullable = false)
    private Integer orp;
    
    @CreationTimestamp
    @Column(name = "measured_at", nullable = false)
    private LocalDateTime measuredAt;
    
    public Parameter() {}

    public Parameter(Long id, Long aquariumId, Double temperature, Double ph, Integer salinity, Integer orp, LocalDateTime measuredAt) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.temperature = temperature;
        this.ph = ph;
        this.salinity = salinity;
        this.orp = orp;
        this.measuredAt = measuredAt;
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
    public Integer getSalinity() {
        return salinity;
    }
    public void setSalinity(Integer salinity) {
        this.salinity = salinity;
    }
    public Integer getOrp() {
        return orp;
    }
    public void setOrp(Integer orp) {
        this.orp = orp;
    }
    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }
    public void setMeasuredAt(LocalDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }
    
    // Metodi di validazione
    public boolean isTemperatureInRange() {
        return temperature != null && temperature >= 24.0 && temperature <= 26.0;
    }
    
    public boolean isPhInRange() {
        return ph != null && ph >= 8.0 && ph <= 8.4;
    }
    
    public boolean isSalinityInRange() {
        return salinity != null && salinity >= 1023 && salinity <= 1026;
    }
    
    public boolean isOrpInRange() {
        return orp != null && orp >= 300 && orp <= 450;
    }
    
    public String getTemperatureStatus() {
        return isTemperatureInRange() ? "ok" : "warning";
    }
    
    public String getPhStatus() {
        return isPhInRange() ? "ok" : "warning";
    }
    
    public String getSalinityStatus() {
        return isSalinityInRange() ? "ok" : "warning";
    }
    
    public String getOrpStatus() {
        return isOrpInRange() ? "ok" : "warning";
    }
}
