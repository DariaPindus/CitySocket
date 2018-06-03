package com.daria.university.diploma.model.messaging.output;


import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SensorMessage {
    protected String name;
    protected Object data;
    protected Long sensorId;
    protected String time;

    public SensorMessage() {
    }

    public SensorMessage(String sensorName, Long sensorId, Object sensorData) {
        this.name = sensorName;
        this.sensorId = sensorId;
        this.data = sensorData;
        this.time = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
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
}

