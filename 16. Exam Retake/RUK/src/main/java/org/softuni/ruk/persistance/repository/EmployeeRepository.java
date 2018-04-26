package org.softuni.ruk.persistance.repository;

import org.softuni.ruk.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findOneByFirstNameAndLastName(final String firstName, final String lastName);

    @Query(value = "Select e " +
            "FROM Employee e " +
            "WHERE e.clients.size > 0 " +
            "ORDER BY e.clients.size DESC, e.id ASC")
    List<Employee> getEmployeesByClients();
}
