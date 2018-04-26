package org.softuni.ruk.persistance.repository;

import org.softuni.ruk.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByFullNameEquals(final String fullName);

    @Query("SELECT c " +
            "FROM Client AS c " +
            "ORDER BY c.bankAccount.cards.size DESC, c.id ASC")
    List<Client> findClientWithMostCards();
}
