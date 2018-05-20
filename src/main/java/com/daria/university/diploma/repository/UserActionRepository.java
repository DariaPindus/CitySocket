package com.daria.university.diploma.repository;


import com.daria.university.diploma.model.dto.UserAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends AbstractRepository<UserAction, Long>{
}
