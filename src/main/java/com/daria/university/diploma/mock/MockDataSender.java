package com.daria.university.diploma.mock;


import com.daria.university.diploma.model.dto.Device;
import com.daria.university.diploma.model.dto.Location;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

public class MockDataSender {
    Map<Long, Device> devices = new HashMap<>();

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public MockDataSender(MessageSendingOperations<String> messagingTemplate){
        this.messagingTemplate = messagingTemplate;
        devices.put(1L, new Device(1L, "Light super 1", "light", new Location(42.12345, 40.44444, "deribasovskaya, 10")));
        devices.put(2L, new Device(2L, "Sound super 1", "sound", new Location(42.12305, 40.45644, "preobrzhenskaya, 10")));
    }

    @Scheduled(fixedDelay = 2000)
    public void sendMockData(){
        devices.forEach((key, val)->{
                SensorMessage mess = getMockMessage(val);
            System.out.println("mesage " + mess.getName() + " " + mess.getData() + " " + mess.getTime());
                messagingTemplate.convertAndSend("/output/" + key, mess);
        });
    }

    public SensorMessage getMockMessage(Device d){;
        int val = new Random().nextInt(100);
        return new SensorMessage(d.getLabel(), d.getId(), val);
    }

}
