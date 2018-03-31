package app.repositories;

import app.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {
}
