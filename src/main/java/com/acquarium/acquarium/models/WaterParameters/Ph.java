package com.acquarium.acquarium.models.WaterParameters;

import java.util.Date;
import com.acquarium.acquarium.models.WaterParameter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PH")
public class Ph extends WaterParameter {

    public Ph(int id, int aquariumId, double value, Date measuredAt, String notes) {
        super(id, aquariumId, value, measuredAt, notes);
    }

    @Override
    public String getUnit() {
        return "";
    }

    @Override
    public double getMinIdealValue() {
        return 8.0; // Per marino
    }

    @Override
    public double getMaxIdealValue() {
        return 8.4;
    }

    @Override
    public String getParameterName() {
        return "pH";
    }
}
