package com.daria.university.diploma.service;


public class SensorStateConverter {
    public static String getSensorState(String type, Object value){
        String state = "";
        if (type.equals("sound")){
            double db = (double)value;
            state = db < 50 ? "ok" : (db < 75 ? "warning" : "danger");
        }
        if (type.equals("val")){
            double lumon = (double)value;
            state = lumon > 75 ? "ok" : (lumon > 50 ? "warning" : "danger");
        }
        return state;
    }
}
