package com.daria.university.diploma.repository;


import com.daria.university.diploma.model.dto.IEntity;
import com.daria.university.diploma.model.dto.SensorData;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

//todo: can not work
public interface SensorDataRepository<E extends SensorData, ID extends Serializable>
        extends AbstractRepository<E, ID>{
    List<E> findByTimeBefore(Timestamp before);
}
