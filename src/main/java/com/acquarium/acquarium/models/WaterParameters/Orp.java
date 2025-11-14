package com.acquarium.acquarium.models.WaterParameters;

import java.util.Date;
import com.acquarium.acquarium.models.WaterParameter;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ORP")
public class Orp extends WaterParameter{

    public Orp(int id, int aquariumId, double value, Date measuredAt, String notes) {
        super(id, aquariumId, value, measuredAt, notes);
    }

    @Override
    public String getUnit() {
        return "mV";
    }

    @Override
    public double getMinIdealValue() {     
        return 300.0;
    }

    @Override
    public double getMaxIdealValue() {
        return 400.0;
    }

    @Override
    public String getParameterName() {    
        return "ORP";
    }
}
