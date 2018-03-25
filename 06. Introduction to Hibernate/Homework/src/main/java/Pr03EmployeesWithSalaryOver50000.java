import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Pr03EmployeesWithSalaryOver50000 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));

        entityManager.close();
        entityManagerFactory.close();
    }
}
