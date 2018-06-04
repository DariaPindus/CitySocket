package com.daria.university.diploma.mock;


import com.daria.university.diploma.model.dto.Device;
import com.daria.university.diploma.model.dto.Location;
import com.daria.university.diploma.model.dto.SoundSensorData;
import com.daria.university.diploma.model.messaging.output.ClientMainDisplayMessage;
import com.daria.university.diploma.model.messaging.output.ClientMainDisplayMessageAdapter;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import com.daria.university.diploma.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MockDataSender {
    @Autowired
    DeviceService deviceService;

    @Value("${app.mock.sending.data:false}")
    boolean mockActive;

    Map<Long, Device> devices = new HashMap<>();

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public MockDataSender(MessageSendingOperations<String> messagingTemplate){
        this.messagingTemplate = messagingTemplate;
        /*devices.put(1L, new Device(1L, "Light super 1", "light", new Location(42.12345, 40.44444, "deribasovskaya, 10")));
        devices.put(2L, new Device(2L, "Sound super 1", "sound", new Location(42.12305, 40.45644, "preobrzhenskaya, 10")));*/
    }

    @PostConstruct
    public void init(){
        devices = deviceService.getAllItems().stream().collect(
                Collectors.toMap(
                        x -> x.getId(), x -> x));
    }

    @Scheduled(fixedDelay = 10000)
    public void sendMockData(){
        if (mockActive) {
            devices.forEach((key, val) -> {
                SensorMessage mess = getMockMessage(val);
                System.out.println("mesage " + mess.getName() + " " + mess.getData() + " " + mess.getTime());
                double rand = new Random().nextDouble() * 100;
                ClientMainDisplayMessage message = new ClientMainDisplayMessageAdapter(new SoundSensorData(val, rand));
                messagingTemplate.convertAndSend("/city/display", message);
            });
        }
    }

    public SensorMessage getMockMessage(Device d){;
        int val = new Random().nextInt(100);
        return new SensorMessage(d, val);
    }

}
