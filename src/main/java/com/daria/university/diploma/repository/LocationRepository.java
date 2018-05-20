package com.daria.university.diploma.repository;

import com.daria.university.diploma.model.dto.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends AbstractRepository<Location, Long>{

}
