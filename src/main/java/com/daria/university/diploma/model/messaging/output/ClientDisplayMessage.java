package com.daria.university.diploma.model.messaging.output;


import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ClientDisplayMessage {

    private int value;
    private String time;
    private String type;    //for test : normal, warning, danger

    public ClientDisplayMessage(){}

    public ClientDisplayMessage(int newValue){
        this.value = newValue;
        this.time = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);
        this.type = value < 50 ? "normal" : value < 100 ? "warning" : "danger";
    }

    public int getValue(){
        return value;
    }

    public String getTime(){
        return time;
    }

    public String getType() {
        return type;
    }
}
