package user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.models.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
}
