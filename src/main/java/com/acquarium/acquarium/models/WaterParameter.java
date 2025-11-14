package com.acquarium.acquarium.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "parameters")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "parameter_type", discriminatorType = DiscriminatorType.STRING)
public abstract class WaterParameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected int id;
    
    @Column(name = "aquarium_id", nullable = false)
    protected int aquariumId;

    @Column(name = "value", nullable = false)
    protected double value;

    @Column(name = "measured_at", nullable = false)
    protected Date measuredAt;

    @Column(name = "notes", nullable = true)
    protected String notes;

    public WaterParameter() {
    }

    public WaterParameter(int id, int aquariumId, double value, Date measuredAt, String notes) {
        this.id = id;
        this.aquariumId = aquariumId;
        this.value = value;
        this.measuredAt = measuredAt;
        this.notes = notes;
    }

    // Metodi astratti che ogni parametro deve implementare
    public abstract String getUnit();
    public abstract double getMinIdealValue();
    public abstract double getMaxIdealValue();
    public abstract String getParameterName();

    // Metodo per verificare se il valore è nel range ideale
    public boolean isInIdealRange() {
        return value >= getMinIdealValue() && value <= getMaxIdealValue();
    }

    // Getters e Setters comuni
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAquariumId() {
        return aquariumId;
    }
    public void setAquariumId(int aquariumId) {
        this.aquariumId = aquariumId;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public Date getMeasuredAt() {
        return measuredAt;
    }
    public void setMeasuredAt(Date measuredAt) {
        this.measuredAt = measuredAt;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}