package org.softuni.mostwanted.persistance.repository;

import org.softuni.mostwanted.model.entity.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Long> {

    @Query(value =
            "SELECT " +
                    "    * " +
                    "FROM " +
                    "    race_entities AS re " +
                    "WHERE " +
                    "    re.racer_id = (SELECT " +
                    "            re.racer_id 'racer' " +
                    "        FROM " +
                    "            race_entities AS re " +
                    "        GROUP BY re.racer_id " +
                    "        ORDER BY COUNT(re.id) DESC " +
                    "        LIMIT 1) " +
                    "ORDER BY re.finish_time", nativeQuery = true)
    List<RaceEntry> getEntriesOfMostWantedRacer();
}
