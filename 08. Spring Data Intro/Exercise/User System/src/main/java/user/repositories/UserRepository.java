package user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
