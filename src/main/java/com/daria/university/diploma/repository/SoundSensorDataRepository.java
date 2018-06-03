package com.daria.university.diploma.repository;


import com.daria.university.diploma.model.dto.SoundSensorData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundSensorDataRepository extends SensorDataRepository<SoundSensorData, Long>{
}
