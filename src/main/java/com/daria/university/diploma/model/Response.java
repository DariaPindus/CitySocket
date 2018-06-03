package com.daria.university.diploma.model;


import java.io.Serializable;

public class Response implements Serializable{
    public boolean success;
    public String message;
    public Object content;

    public Response(){}

    public Response(boolean success, Object content) {
        this.success = success;
        this.content = content;
        this.content = "";
    }

    public Response(boolean success, String message, Object content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }
}
