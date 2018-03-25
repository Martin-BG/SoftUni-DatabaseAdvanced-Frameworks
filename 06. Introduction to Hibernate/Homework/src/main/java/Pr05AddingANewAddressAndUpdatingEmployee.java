import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Pr05AddingANewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        try {
            Employee employee = entityManager
                    .createQuery("SELECT e FROM Employee AS e WHERE e.lastName = :lastName", Employee.class)
                    .setParameter("lastName", lastName)
                    .getSingleResult();

            Town sofia = entityManager
                    .createQuery("SELECT t FROM Town AS t WHERE t.name = 'Sofia'", Town.class)
                    .getSingleResult();

            Address address = new Address();
            address.setText("Vitoshka 15");
            address.setTown(sofia);

            entityManager.getTransaction().begin();

            entityManager.persist(address);

            employee.setAddress(address);

            entityManager.getTransaction().commit();

            System.out.printf("%s %s changed address to %s, %s%n",
                    employee.getFirstName(), employee.getLastName(),
                    employee.getAddress().getText(), employee.getAddress().getTown().getName());

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
