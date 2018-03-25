import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Pr10RemoveTowns {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print("Enter town name: ");
        String townName = scanner.nextLine().trim();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Town town = entityManager.createQuery("SELECT t FROM Town AS t WHERE t.name = :townName", Town.class)
                    .setParameter("townName", townName)
                    .getSingleResult();

            List<Address> addresses = entityManager
                    .createQuery("SELECT a FROM Address AS a WHERE a.town.name = :townName", Address.class)
                    .setParameter("townName", townName)
                    .getResultList();

            String output = String.format("%d address%s in %s deleted%n",
                    addresses.size(), (addresses.size() != 1) ? "es" : "", town.getName());

            entityManager.getTransaction().begin();

            addresses.forEach(address -> {
                for (Employee employee : address.getEmployees()) {
                    employee.setAddress(null);
                }
                address.setTown(null);
                entityManager.remove(address);
            });

            entityManager.remove(town);

            entityManager.getTransaction().commit();

            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
