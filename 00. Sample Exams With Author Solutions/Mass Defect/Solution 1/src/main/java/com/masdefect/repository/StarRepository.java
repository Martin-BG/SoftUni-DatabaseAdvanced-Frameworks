package com.masdefect.repository;

import com.masdefect.domain.entities.Star;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends CrudRepository<Star, Long> {
    Star findOneByName(String name);
}
