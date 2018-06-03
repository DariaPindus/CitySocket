package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.Device;
import com.daria.university.diploma.model.dto.Location;
import com.daria.university.diploma.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService extends AbstractService<Device, Long>{

    @Autowired
    DeviceRepository repository;

    public DeviceService(){
        setDao(repository);
    }

    @PostConstruct
    public void postConstruct(){

        if(repository.findAll().isEmpty()) {
            Device device1 = new Device("super device 1", "sound", new Location(46.484864, 30.735370, "Hrecheskaya st."));
            Device device2 = new Device("super device 2", "sound", new Location(46.484864, 30.735370, "Deribasovskaya st."));
            Device device3 = new Device("super device 3", "light", new Location(46.484864, 30.735370, "Bunina st."));

            List<Device> devices = new ArrayList<>();
            devices.add(device1);
            devices.add(device2);
            devices.add(device3);

            repository.save(devices);
        }
    }
}
