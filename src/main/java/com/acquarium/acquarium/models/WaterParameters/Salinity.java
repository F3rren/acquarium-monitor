package com.acquarium.acquarium.models.WaterParameters;

import java.util.Date;
import com.acquarium.acquarium.models.WaterParameter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SALINITY")
public class Salinity extends WaterParameter {

    public Salinity(int id, int aquariumId, double value, Date measuredAt, String notes) {
        super(id, aquariumId, value, measuredAt, notes);
    }

    @Override
    public String getUnit() {
        return "PPT";
    }

    @Override
    public double getMinIdealValue() {
        return 1023.0;
    }

    @Override
    public double getMaxIdealValue() {
        return 1027.0;
    }

    @Override
    public String getParameterName() {
        return "Salinità";
    }
    
}
