import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Pr02ContainsEmployee {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter employee's first and last names: ");
        String[] employeeNames = scanner.nextLine().trim().split("\\s+");

        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.firstName = :firstName AND e.lastName = :lastName", Employee.class)
                .setParameter("firstName", employeeNames[0])
                .setParameter("lastName", employeeNames[1])
                .getResultList();

        if (!employees.isEmpty()) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
