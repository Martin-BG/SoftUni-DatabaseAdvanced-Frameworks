package org.softuni.mostwanted.persistance.repository;

import org.softuni.mostwanted.model.dto.view.json.TownsByRacersExportDto;
import org.softuni.mostwanted.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Town findOneByName(final String name);

    @Query(value = "SELECT NEW " +
            "org.softuni.mostwanted.model.dto.view.json.TownsByRacersExportDto(r.homeTown.name, COUNT(r.homeTown.id)) " +
            "FROM Racer AS r " +
            "GROUP BY r.homeTown.id " +
            "ORDER BY COUNT(r.homeTown.id) DESC, r.homeTown.name ASC")
    List<TownsByRacersExportDto> findTownsByRacers();
}
