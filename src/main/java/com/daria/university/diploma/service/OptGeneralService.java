package com.daria.university.diploma.service;


import java.io.Serializable;
import java.util.Optional;

public interface OptGeneralService<E extends Serializable, ID  extends Serializable> {
    E save(E entity);
    E delete(E entity);
    E deleteById(ID key);
    E update(E entity);
    Iterable<E> findByValue(String column, Object value);
    Iterable<E> getAllItems();
    Optional<E> findById(ID id);
    Optional<E> findByName(String name);
    Iterable<E> saveBatch(Iterable<E> records);
}

