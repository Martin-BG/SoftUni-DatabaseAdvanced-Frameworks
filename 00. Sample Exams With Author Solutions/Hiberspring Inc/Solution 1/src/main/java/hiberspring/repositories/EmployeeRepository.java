package hiberspring.repositories;

import hiberspring.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT count(e) FROM Employee AS e INNER JOIN e.card AS ec WHERE ec.number = :cardNumber")
    long getEmployeeCountByEmployeeCardNumber(@Param("cardNumber") String cardNumber);

    @Query(value = "SELECT e" +
            " 	FROM Employee AS e, " + 
    	    "	Branch AS b," +
            "	Product AS p" +
            "	WHERE e.branch.id = b.id" +
            "	AND b.id = p.branch.id" +
            "	ORDER BY concat(e.firstName, e.lastName) ASC, length(e.position) DESC")
    List<Employee> getProductiveEmployees();
}
