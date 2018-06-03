package com.daria.university.diploma.model.dto;

import com.daria.university.diploma.utils.TimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
//@Entity
//@Inheritance
public class SensorData implements IEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "time")
    private Timestamp time;

    public SensorData() {
    }

    public SensorData(Device device) {
        this.device = device;
    }

    public Long getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public DateTime getTime() {
        return new DateTime(time.getTime());
    }

    public void setTime(DateTime time) {
        this.time = new Timestamp(time.getMillis());
    }

    @PrePersist
    public void prePersist(){
        this.time = TimeUtil.getCurrentTime();
    }
}
