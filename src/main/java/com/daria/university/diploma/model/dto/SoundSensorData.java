package com.daria.university.diploma.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sound_data")
public class SoundSensorData extends SensorData {

    @Column(name = "dB")
    private double db;

    public double getDb() {
        return db;
    }

    public void setDb(double db) {
        this.db = db;
    }
}
