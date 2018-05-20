package hiberspring.repositories;

import hiberspring.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long> {

    EmployeeCard getEmployeeCardByNumber(String number);

    @Query(value = "SELECT ec" +
            "  FROM Employee AS e" +
            " RIGHT JOIN  e.card AS ec" +
            " WHERE e.card IS NULL" +
            " ORDER BY ec")
    List<EmployeeCard> getUnusedCards();
}
