package org.softuni.ruk.persistance.repository;

import org.softuni.ruk.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Branch findOneByName(String name);
}
