package com.photographyworkshops.repository;

import com.photographyworkshops.domain.entities.workshops.Workshop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends CrudRepository<Workshop, Long> {

    @Query(value = "SELECT w FROM Workshop AS w " +
            "INNER JOIN w.photographers AS p " +
            "GROUP BY w " +
            "HAVING COUNT(p) >= 5")
    List<Workshop> findAllByLocation();
}
