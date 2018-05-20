package com.daria.university.diploma.service;

import com.daria.university.diploma.model.dto.UserAction;
import com.daria.university.diploma.repository.UserActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserActionService extends AbstractService<UserAction, Long>{
    @Autowired
    UserActionRepository repository;

    public UserActionService(){
        setDao(repository);
    }
}
