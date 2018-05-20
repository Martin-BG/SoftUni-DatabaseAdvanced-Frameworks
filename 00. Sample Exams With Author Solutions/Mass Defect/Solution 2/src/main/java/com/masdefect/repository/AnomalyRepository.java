package com.masdefect.repository;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.entities.Anomaly;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyRepository extends CrudRepository<Anomaly, Long> {

    @Query(value = "SELECT a FROM Anomaly AS a " +
            "INNER JOIN a.persons AS p " +
            "GROUP BY a " +
            "ORDER BY COUNT(p) DESC")
    List<Anomaly> findMostAffectingAnomalies();

    @Query(value = "SELECT a FROM Anomaly AS a ORDER BY a.persons.size, a.id")
    List<Anomaly> finaAllAnomalies();
}
