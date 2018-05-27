package com.daria.university.diploma.controllers;

import com.daria.university.diploma.model.messaging.output.ClientActuatorMessage;
import com.daria.university.diploma.model.messaging.output.ClientDisplayMessage;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import com.daria.university.diploma.service.SensorDataService;
import com.daria.university.diploma.service.UserActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebsocketMainController {

    @Autowired
    SensorDataService sensorDataService;

    @Autowired
    UserActionService userActionService;
    /*
    @MessageMapping("/output") // + /{deviceId}
    @SendTo("/city/display")
    public ClientDisplayMessage first(SensorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN FIRST: {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        return new ClientDisplayMessage(Integer.valueOf((String)message.getData()));
    }

    @MessageMapping("/input/sound") // + /{deviceId}
    @SendTo("/sensors/greetings")
    public SensorMessage actuateSoundSensor(ClientActuatorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN SECOND : {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        return new SensorMessage(message.getSensorName(), message.getValue());
    }

    @MessageMapping("/input/sound") // + /{deviceId}
    @SendTo("/sensors/greetings")
    public SensorMessage actuateLightSensor(ClientActuatorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN SECOND : {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        return new SensorMessage(message.getSensorName(), message.getValue());
    }
*/
    @MessageMapping("/output/{type}")
    @SendTo("/city/display")
    public ClientDisplayMessage first(@DestinationVariable String type, SensorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN FIRST: {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);

        return new ClientDisplayMessage(Integer.valueOf((String)message.getData()));
    }

    @MessageMapping("/input") //todo: or /input/{type}
    @SendTo("/sensors")
    public ClientActuatorMessage second(ClientActuatorMessage message) throws Exception {
        //log.info("::::::::::::Received hello IN SECOND : {}::::::::::::", message.getName());
        System.out.println("::::::::::::Received hello IN FIRST: {}::::::::::::" +  message);
        //userActionService.registerAction(message);
        return message;
    }

    @MessageExceptionHandler
    @SendTo("/city/display/error")
    public String handleException(IllegalArgumentException ex) {
        return "Got error: " + ex.getMessage();
    }


}