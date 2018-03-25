import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Pr11FindEmployeesByFirstName {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter first name staring letters: ");
        String p = scanner.nextLine().trim();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE :p", Employee.class)
                    .setParameter("p", p + "%")
                    .getResultList()
                    .forEach(employee -> System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(),
                            employee.getLastName(), employee.getJobTitle(), employee.getSalary()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
