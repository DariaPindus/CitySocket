package com.daria.university.diploma.model.messaging.output;


public class ClientActuatorMessage {
    private String sensorName;
    private Object value;

    public ClientActuatorMessage(){}

    public ClientActuatorMessage(String sensorName, Object value) {
        this.sensorName = sensorName;
        this.value = value;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString(){
        return "sensorName " + sensorName + ", value " + value;
    }
}
