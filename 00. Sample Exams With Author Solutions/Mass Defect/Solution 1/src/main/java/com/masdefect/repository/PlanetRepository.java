package com.masdefect.repository;

import com.masdefect.domain.entities.Planet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {

    Planet findOneByName(String name);

    @Query(value = "SELECT p FROM Planet AS p " +
            "LEFT JOIN p.anomalies AS a " +
            "WHERE a.id IS NULL")
    List<Planet> findAllPlanetsWithoutPeopleTeleportedFromThem();
}
