package com.masdefect.repository;

import com.masdefect.domain.entities.SolarSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarSystemRepository extends CrudRepository<SolarSystem, Long> {
    SolarSystem findOneByName(String name);
}
