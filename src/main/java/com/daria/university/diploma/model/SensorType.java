package com.daria.university.diploma.model;


public enum SensorType {
    SOUND ("sound"),
    LIGHT ("light");

    private final String labelName;

    SensorType(String labelName){
        this.labelName = labelName;
    }

    public String labelName(){
        return labelName;
    }
}
