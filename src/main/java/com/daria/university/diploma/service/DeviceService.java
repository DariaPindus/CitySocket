package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.Device;
import com.daria.university.diploma.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService extends AbstractService<Device, Long>{

    @Autowired
    DeviceRepository repository;

    public DeviceService(){
        setDao(repository);
    }
}
