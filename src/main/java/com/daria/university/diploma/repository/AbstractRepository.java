package com.daria.university.diploma.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface AbstractRepository<T extends Serializable, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor
{

    //Optional<T> findOneByName(String name); will fail in case entity doesn't have field "name"
    Optional<T> findOneById(ID id);
}
