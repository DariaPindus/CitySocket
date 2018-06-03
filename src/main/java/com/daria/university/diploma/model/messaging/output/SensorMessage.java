package com.daria.university.diploma.model.messaging.output;


import com.daria.university.diploma.model.dto.Device;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SensorMessage {
    protected String name;
    protected Object data;
    protected Long deviceId;
    protected Device device;
    protected String time;
    protected String type;

    public SensorMessage() {
    }

    public SensorMessage(String sensorName, Long deviceId, String type, Object sensorData) {
        this.name = sensorName;
        this.deviceId = deviceId;
        this.type = type;
        this.data = sensorData;
        this.time = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public SensorMessage(Device device, Object sensorData){
        this.device = device;
        this.name = device.getLabel();
        this.deviceId = device.getId();
        this.type = device.getType();
        this.data = sensorData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "name " + name + " , data " + data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

