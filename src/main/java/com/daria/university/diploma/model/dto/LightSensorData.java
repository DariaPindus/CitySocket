package com.daria.university.diploma.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "light_data")
public class LightSensorData extends SensorData {

    @Column(name = "luminosity")
    private double luminosity;

    public LightSensorData(){}

    public LightSensorData(Device device, double luminosity){
        super(device);
        this.luminosity = luminosity;
    }

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }
}
