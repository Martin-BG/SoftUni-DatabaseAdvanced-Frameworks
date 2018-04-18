package car_dealer.persistance.repository;

import car_dealer.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
