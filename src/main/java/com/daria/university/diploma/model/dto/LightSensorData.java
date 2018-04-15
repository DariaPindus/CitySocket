package com.daria.university.diploma.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "light_data")
public class LightSensorData {

    @Column(name = "illuminance")
    private double illuminance;

    public double getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(double illuminance) {
        this.illuminance = illuminance;
    }
}
