package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.Location;
import com.daria.university.diploma.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LocationService extends AbstractService<Location, Long> {

    @Autowired
    LocationRepository repository;

    public LocationService(){
        setDao(repository);
    }

    @PostConstruct
    public void init(){
        repository.save(new Location(46.484864, 30.735370, "Hrecheskaya st."));
        repository.save(new Location(46.484515, 30.730778, "Deribasovskaya St., 11"));
        repository.save(new Location(46.480149, 30.731370, "Tirapolskaya sq."));
    }

    public Location save(Location location){
        return repository.save(location);
    }
}
