package app.repositories;

import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographersRepository extends JpaRepository<Photographer, Long> {

    Photographer findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT p FROM Photographer AS p ORDER BY p.firstName ASC , p.lastName DESC")
    List<Photographer> findAllOrdered();

    @Query(value = "SELECT p FROM Photographer AS p WHERE p.primaryCamera.make = p.secondaryCamera.make")
    List<Photographer> findAllWithSameCameras();
}
