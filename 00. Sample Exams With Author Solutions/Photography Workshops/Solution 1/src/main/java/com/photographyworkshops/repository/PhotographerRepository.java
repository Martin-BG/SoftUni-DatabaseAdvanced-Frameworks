package com.photographyworkshops.repository;

import com.photographyworkshops.domain.entities.photographers.Photographer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends CrudRepository<Photographer, Long>{

    Photographer findOneByFirstNameAndLastName(String trainerFirstName, String trainerLastName);

    @Query(value = "SELECT p FROM Photographer AS p " +
            "ORDER BY p.firstName ASC, p.lastName DESC")
    List<Photographer> findAllOrder();

    @Query(value = "SELECT p FROM Photographer AS p " +
            "INNER JOIN p.lenses AS l " +
            "WHERE l.focalLength <= 30 " +
            "ORDER BY p.firstName ASC")
    List<Photographer> findAllWithLenses();


    @Query(value = "SELECT p FROM Photographer AS p " +
            "WHERE p.primaryCamera.make <> p.secondaryCamera.make " +
            "AND p.lenses.size > 0")
    List<Photographer> findAllWithCameras();
}
