package org.softuni.mostwanted.persistance.repository;

import org.softuni.mostwanted.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    District findOneByName(final String name);
}
