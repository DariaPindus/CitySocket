package com.daria.university.diploma.model.messaging.output;


import com.daria.university.diploma.model.dto.Device;

public class ClientMainDisplayMessage {

    private final String type;
    private final Device device;
    private final Object value;
    private final String state;

    public ClientMainDisplayMessage(Device device, Object value, String state) {
        this.type = device.getType();
        this.device = device;
        this.value = value;
        this.state = state;
    }
}
