package com.daria.university.diploma.service;

import java.util.List;

public interface GeneralService<E> {
    Iterable<E> getAllItems();
    E findByName(String name);
    E save(E entity);
    E delete(E entity);
    E deleteByKey(Object key);
    E update(E entity);
    E findByValue(String column, String value);
}
