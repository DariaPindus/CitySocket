package com.daria.university.diploma.service;


import com.daria.university.diploma.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class AbstractService<T extends Serializable, ID  extends Serializable> implements OptGeneralService<T, ID>{

    @Autowired
    AbstractRepository<T, ID> repository;

    //TODO: add validity check
    public AbstractService(AbstractRepository<T, ID> dao){
        this.repository = dao;
    }

    public AbstractService(){}

    public void setDao(AbstractRepository<T, ID> dao){
        this.repository = dao;
    };

    @Transactional
    @Override
    public List<T> getAllItems() {
        return repository.findAll();
    }

    /*@Transactional
    @Override
    public Optional<T> findByName(String name) {
        return repository.findOneByName(name);
    }*/

    @Override
    @Transactional
    public Optional<T> findById(ID id) {
        return repository.findOneById(id);
    }

    @Transactional
    public T deleteById(ID recordId) {
        T record = repository.findOneById(recordId).get();
        return delete(record);
    }

    @Transactional
    @Override
    public T delete(T record){
        repository.delete(record);
        return record;
    }

    @Transactional
    @Override
    public T save(T record) {
        return repository.save(record);
    }

    @Transactional
    @Override
    public T update(T record) {
        return repository.save(record);
    }

    @Transactional
    public Iterable<T> saveBatch(Iterable<T> records){
        return repository.save(records);
    }

    public Iterable<T> findByValue(String column, Object value){
        return repository.findAll((root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get(column), value)
        );
    }

}
