package org.softuni.mostwanted.persistance.repository;

import org.softuni.mostwanted.model.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Long> {

    Racer findOneByName(final String name);

    @Query("SELECT r " +
            "FROM Racer AS r " +
            "WHERE r.cars.size > 0 " +
            "ORDER BY r.cars.size DESC, r.name ASC")
    List<Racer> getAllRacersWithCars();
}
