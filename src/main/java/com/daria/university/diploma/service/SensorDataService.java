package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.*;
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
        List<Device> devices = (List<Device>)deviceService.getAllItems();

        List<SensorData> sensorDatas = new ArrayList<>();
        saveSensorData("sound",new SoundSensorData(devices.get(0), 13.13));
        saveSensorData("sound",new SoundSensorData(devices.get(1), 20.13));
        saveSensorData("sound",new SoundSensorData(devices.get(1), 17.13));
        saveSensorData("sound",new SoundSensorData(devices.get(2), 17.13));
    }

    @Transactional
    public void saveSensorData(String type, SensorData data){
        if (type == "sound")
            soundSensorDataRepository.save((SoundSensorData)data);  //todo : check it saves all fields
        if (type == "light")
            lightSensorDataRepository.save((LightSensorData)data);
    }

    @Transactional
    public List<SensorData> getDataForLastDays(int nDays){
        List<SensorData> result = new ArrayList<>();
        Timestamp timestamp = TimeUtil.getDaysBefore(nDays);
        result.addAll(soundSensorDataRepository.findByTimeBefore(timestamp));
        result.addAll(lightSensorDataRepository.findByTimeBefore(timestamp));
        return result;
    }
}
