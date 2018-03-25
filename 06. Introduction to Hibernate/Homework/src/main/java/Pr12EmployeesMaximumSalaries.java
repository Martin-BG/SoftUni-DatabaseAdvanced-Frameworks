import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class Pr12EmployeesMaximumSalaries {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder employees = new StringBuilder();

        entityManager
                .createQuery("SELECT e FROM  Employee AS e " +
                        "WHERE e.salary NOT BETWEEN 30000 AND 70000 " +
                        "GROUP BY e.department " +
                        "ORDER BY e.salary DESC", Employee.class)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(e -> e.getDepartment().getId()))
                .forEach(employee -> employees.append(String.format("%s - %.2f%n",
                        employee.getDepartment().getName(), employee.getSalary())));

        entityManager.close();
        entityManagerFactory.close();

        System.out.println(employees.toString().trim());
    }
}
