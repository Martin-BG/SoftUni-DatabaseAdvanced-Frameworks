package com.masdefect.repository;

import com.masdefect.domain.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findOneByName(String person);

    @Query(value = "SELECT p FROM Person AS p " +
            "LEFT JOIN p.anomalies AS a " +
            "WHERE a.id IS NULL")
    List<Person> findInnocentPersons();
}
