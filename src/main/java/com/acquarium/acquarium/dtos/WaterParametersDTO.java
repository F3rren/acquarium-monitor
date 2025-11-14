// WaterParametersDTO.java
package com.acquarium.acquarium.dtos;

import java.time.LocalDateTime;

public class WaterParametersDTO {
    
    private int id;
    private double temperature;
    private double ph;
    private double salinity;
    private double orp;
    private LocalDateTime timestamp;

    public WaterParametersDTO() {
    }

    public WaterParametersDTO(int id, double temperature, double ph, double salinity, double orp, LocalDateTime timestamp) {
        this.id = id;
        this.temperature = temperature;
        this.ph = ph;
        this.salinity = salinity;
        this.orp = orp;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public double getPh() {
        return ph;
    }
    public void setPh(double ph) {
        this.ph = ph;
    }
    public double getSalinity() {
        return salinity;
    }
    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }
    public double getOrp() {
        return orp;
    }
    public void setOrp(double orp) {
        this.orp = orp;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}