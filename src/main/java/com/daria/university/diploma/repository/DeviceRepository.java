package com.daria.university.diploma.repository;


import com.daria.university.diploma.model.dto.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends AbstractRepository<Device, Long >{
}
