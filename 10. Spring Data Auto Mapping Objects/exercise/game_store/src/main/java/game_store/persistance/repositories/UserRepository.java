package game_store.persistance.repositories;

import game_store.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailEquals(final String email);

    User findByEmailAndPassword(final String email, final String password);
}
