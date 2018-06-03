package com.daria.university.diploma.repository;


import com.daria.university.diploma.model.dto.IEntity;
import com.daria.university.diploma.model.dto.SensorData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

//todo: can not work
@NoRepositoryBean
public interface SensorDataRepository<E extends SensorData, ID extends Serializable>
        extends AbstractRepository<E, ID>{

    @Query("select s from #{#entityName} as s where s.time > ?1 ")
    List<E> findByTimeAfter(Timestamp after);

    @Query("select s from #{#entityName} as s where s.device=?1 and s.time > ?2 order by s.time desc")
    List<E> findByDeviceAndTimeAfter(Long id, Timestamp after);
}
