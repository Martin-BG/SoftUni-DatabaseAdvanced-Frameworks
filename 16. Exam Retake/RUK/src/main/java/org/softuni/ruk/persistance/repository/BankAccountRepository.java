package org.softuni.ruk.persistance.repository;

import org.softuni.ruk.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findOneByAccountNumber(final String accountNumber);
}
