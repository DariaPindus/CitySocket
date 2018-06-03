package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.*;
import com.daria.university.diploma.model.messaging.output.SensorMessage;
import com.daria.university.diploma.repository.LightSensorDataRepository;
import com.daria.university.diploma.repository.SoundSensorDataRepository;
import com.daria.university.diploma.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class SensorDataService {

    @Autowired
    SoundSensorDataRepository soundSensorDataRepository;

    @Autowired
    LightSensorDataRepository lightSensorDataRepository;

    @Autowired
    DeviceService deviceService;

    @PostConstruct
    public void initialize(){
        if (soundSensorDataRepository.findAll().isEmpty() && lightSensorDataRepository.findAll().isEmpty()) {
            List<Device> devices = (List<Device>) deviceService.getAllItems();

            List<SensorData> sensorDatas = new ArrayList<>();
            saveSensorData("sound", new SoundSensorData(devices.get(0), 13.13));
            saveSensorData("sound", new SoundSensorData(devices.get(1), 20.13));
            saveSensorData("sound", new SoundSensorData(devices.get(1), 17.13));
            saveSensorData("sound", new SoundSensorData(devices.get(2), 17.13));
        }
    }

    //todo: dirty, make it clearer
    @Transactional
    public SensorData saveSensorMessage(SensorMessage message){
        SensorData res = null;
        if (message.getType() == "sound"){
            res = new SoundSensorData(new Device(message.getDeviceId()), (double)message.getData());
        }
        if (message.getType() == "light"){
            res = new LightSensorData(new Device(message.getDeviceId()), (double)message.getData());
        }
        return saveSensorData(message.getType(), res);
    }

    @Transactional
    public SensorData saveSensorData(String type, SensorData data){
        SensorData res = null;
        if (type == "sound")
            res = soundSensorDataRepository.save((SoundSensorData)data);  //todo : check it saves all fields
        if (type == "light")
            res = lightSensorDataRepository.save((LightSensorData)data);
        return res;
    }

    @Transactional
    public List<SensorData> getDataForLastDays(int nDays){
        List<SensorData> result = new ArrayList<>();
        Timestamp timestamp = TimeUtil.getDaysBefore(nDays);
        result.addAll(soundSensorDataRepository.findByTimeAfter(timestamp));
        result.addAll(lightSensorDataRepository.findByTimeAfter(timestamp));
        return result;
    }


    @Transactional
    public List<SensorData> getAll(){
        List<SensorData> sensorDatas = new ArrayList<>();
        List<SoundSensorData> allSoundData = soundSensorDataRepository.findAll();
        List<LightSensorData> allLightData = lightSensorDataRepository.findAll();
        sensorDatas.addAll(allSoundData);
        sensorDatas.addAll(allLightData);

        return sensorDatas;
    }
}
