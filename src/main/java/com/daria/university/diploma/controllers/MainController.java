package com.daria.university.diploma.controllers;

import com.daria.university.diploma.model.messaging.input.Message;
import com.daria.university.diploma.model.messaging.output.ClientActuatorMessage;
import com.daria.university.diploma.model.messaging.output.ClientDisplayMessage;
import com.daria.university.diploma.model.messaging.output.OutputMessage;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

    /*
        mapping conventions :
        city - output for js client (it subscribes to it, controller routes messages from sensor server here
        output - sensor server send messages here
        sensors - user interactions are sent here and routed to sensor server listener
     */

    //??? not conflicting with setting???
    /* on /sensors/{sensorName} is sent message from Sensor (SensorMessage);
     *  it's included in params
     * then it transports to client
     * as clientmessage that will be displayed
     */
    @MessageMapping("/sensors/{sensorName}")
    @SendTo("/output/{sensorName}")
    public ClientDisplayMessage send(@DestinationVariable String sensorName, SensorMessage message) throws Exception {
        System.out.println("sensorName " + sensorName + ", message " + message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ClientDisplayMessage(message.getSensorData());
    }

    @MessageMapping("/output/{sensorName}")
    @SendTo("/city/{sensorName}")
    public ClientDisplayMessage receiveSensorData(@DestinationVariable String sensorName, SensorMessage message) throws Exception {
        System.out.println("sensorName " + sensorName + ", message " + message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new ClientDisplayMessage(message.getSensorData());
    }

    /* message is received from Client UI and sent with action to SensorManager
     *
     * user clicks button with destination stompClient.send("/app/{sensorName}.alter"
     *
     */
    // ??? will such mapping path even work ???
    // or /city/alter/{sensorName} is better
    @MessageMapping("/city/{sensorName}.alter")
    @SendTo("/sensors/{sensorName}.alter")
    public ClientActuatorMessage alter(@DestinationVariable String sensorName, ClientActuatorMessage message){
        System.out.println("alter : sensorName " + sensorName + ", message " + message);
        return message;
    }

}
