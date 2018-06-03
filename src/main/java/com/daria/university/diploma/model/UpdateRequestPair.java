package com.daria.university.diploma.model;


import java.io.Serializable;

public class UpdateRequestPair implements Serializable {

    private String fieldName;
    private Object value;

    public UpdateRequestPair(){}

    public UpdateRequestPair(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
