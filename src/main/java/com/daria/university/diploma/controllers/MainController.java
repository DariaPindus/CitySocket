package com.daria.university.diploma.controllers;

import com.daria.university.diploma.model.messaging.output.ClientActuatorMessage;
import com.daria.university.diploma.model.messaging.output.ClientDisplayMessage;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MainController {

    @MessageMapping("/output")
    @SendTo("/city/display")
    public ClientDisplayMessage first(SensorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN FIRST: {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        return new ClientDisplayMessage(Integer.valueOf((String)message.getData()));
    }

    @MessageMapping("/input")
    @SendTo("/sensors/greetings")
    public SensorMessage second(ClientActuatorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN SECOND : {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        return new SensorMessage(message.getSensorName(), message.getValue());
    }

    @MessageExceptionHandler
    @SendTo("/city/display/error")
    public String handleException(IllegalArgumentException ex) {
        return "Got error: " + ex.getMessage();
    }

}