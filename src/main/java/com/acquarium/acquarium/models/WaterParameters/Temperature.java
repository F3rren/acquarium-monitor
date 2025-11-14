package com.acquarium.acquarium.models.WaterParameters;

import java.util.Date;
import com.acquarium.acquarium.models.WaterParameter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TEMPERATURE")
public class Temperature extends WaterParameter {

    public Temperature(int id, int aquariumId, double value, Date measuredAt, String notes) {
        super(id, aquariumId, value, measuredAt, notes);
    }

    @Override
    public String getUnit() {
        return "°C";
    }

    @Override
    public double getMinIdealValue() {
        return 24.0; // Modificabile in base al tipo di acquario
    }

    @Override
    public double getMaxIdealValue() {
        return 26.0;
    }

    @Override
    public String getParameterName() {
        return "Temperature";
    }    
}
        