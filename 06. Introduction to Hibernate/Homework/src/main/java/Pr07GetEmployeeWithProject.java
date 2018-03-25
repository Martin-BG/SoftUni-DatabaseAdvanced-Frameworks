import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;

public class Pr07GetEmployeeWithProject {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter employee id: ");
        Integer id = Integer.valueOf(scanner.nextLine());

        try {
            Employee employee = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();

            StringBuilder sb = new StringBuilder();

            sb.append(employee.getFirstName()).append(" ").append(employee.getLastName())
                    .append(" - ").append(employee.getJobTitle()).append(System.lineSeparator());

            employee.getProjects().stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(project -> sb.append("\t").append(project.getName()).append(System.lineSeparator()));

            System.out.println(sb.toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
