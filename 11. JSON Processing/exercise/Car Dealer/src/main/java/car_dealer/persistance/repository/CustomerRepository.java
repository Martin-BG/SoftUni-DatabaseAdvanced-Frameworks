package car_dealer.persistance.repository;

import car_dealer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.isYoungDriver")
    List<Customer> getAllByBirthdate();
}
