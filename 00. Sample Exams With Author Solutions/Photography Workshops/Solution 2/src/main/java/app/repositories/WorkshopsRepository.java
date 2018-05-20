package app.repositories;

import app.model.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopsRepository extends JpaRepository<Workshop, Long> {

}
