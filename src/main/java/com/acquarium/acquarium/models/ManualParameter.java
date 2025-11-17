package com.acquarium.acquarium.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "manual_parameters")
public class ManualParameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "aquarium_id", nullable = false)
    private Long aquariumId;
    
    @Column(name = "calcium")
    private Double calcium;
    
    @Column(name = "magnesium")
    private Double magnesium;
    
    @Column(name = "kh")
    private Double kh;
    
    @Column(name = "nitrate")
    private Double nitrate;
    
    @Column(name = "phosphate")
    private Double phosphate;
    
    @CreationTimestamp
    @Column(name = "measured_at", nullable = false)
    private LocalDateTime measuredAt;
    
    @Column(name = "notes")
    private String notes;
    
    public ManualParameter() {}

    public ManualParameter(Long id, Long aquariumId, Double calcium, Double magnesium, Double kh, 
                          Double nitrate, Double phosphate, LocalDateTime measuredAt, String notes) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.kh = kh;
        this.nitrate = nitrate;
        this.phosphate = phosphate;
        this.measuredAt = measuredAt;
        this.notes = notes;
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
    public Double getCalcium() {
        return calcium;
    }
    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }
    public Double getMagnesium() {
        return magnesium;
    }
    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }
    public Double getKh() {
        return kh;
    }
    public void setKh(Double kh) {
        this.kh = kh;
    }
    public Double getNitrate() {
        return nitrate;
    }
    public void setNitrate(Double nitrate) {
        this.nitrate = nitrate;
    }
    public Double getPhosphate() {
        return phosphate;
    }
    public void setPhosphate(Double phosphate) {
        this.phosphate = phosphate;
    }
    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }
    public void setMeasuredAt(LocalDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    // Metodi di validazione range ideali
    public boolean isCalciumInRange() {
        return calcium != null && calcium >= 400 && calcium <= 450;
    }
    
    public boolean isMagnesiumInRange() {
        return magnesium != null && magnesium >= 1250 && magnesium <= 1350;
    }
    
    public boolean isKhInRange() {
        return kh != null && kh >= 8.0 && kh <= 12.0;
    }
    
    public boolean isNitrateInRange() {
        return nitrate != null && nitrate >= 0 && nitrate <= 10.0;
    }
    
    public boolean isPhosphateInRange() {
        return phosphate != null && phosphate >= 0 && phosphate <= 0.05;
    }
    
    public String getCalciumStatus() {
        return isCalciumInRange() ? "ok" : "warning";
    }
    
    public String getMagnesiumStatus() {
        return isMagnesiumInRange() ? "ok" : "warning";
    }
    
    public String getKhStatus() {
        return isKhInRange() ? "ok" : "warning";
    }
    
    public String getNitrateStatus() {
        return isNitrateInRange() ? "ok" : "warning";
    }
    
    public String getPhosphateStatus() {
        return isPhosphateInRange() ? "ok" : "warning";
    }
}
