package com.daria.university.diploma.model.messaging.output;


import com.daria.university.diploma.model.dto.Device;

import java.io.Serializable;

public class ClientMainDisplayMessage implements Serializable {

    private String type;
    private String deviceName;
    private Long deviceId;
    private String location;
    private Object value;
    private String state;

    public ClientMainDisplayMessage(){}

    public ClientMainDisplayMessage(Device device, Object value, String state) {
        this.type = device.getType();
        this.deviceId = device.getId();
        this.deviceName = device.getLabel();
        this.location = device.getLocation().getLabel();
        this.value = value;
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
