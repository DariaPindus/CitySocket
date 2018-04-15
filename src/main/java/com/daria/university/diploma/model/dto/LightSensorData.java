package com.daria.university.diploma.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "light_data")
public class LightSensorData {

    @Column(name = "luminosity")
    private double luminosity;

    public double getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double luminosity) {
        this.luminosity = luminosity;
    }
}
