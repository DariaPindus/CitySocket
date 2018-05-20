package com.daria.university.diploma.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.Optional;


public interface AbstractRepository<T extends Serializable, ID extends Serializable> extends CrudRepository<T, ID>,
        JpaSpecificationExecutor
{

    Optional<T> findOneByName(String name);
    Optional<T> findOneById(ID id);
}
