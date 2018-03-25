import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Pr06AddressesWithEmployeeCount {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder addresses = new StringBuilder();

        entityManager
                .createQuery("SELECT a FROM  Address AS a ORDER BY a.employees.size DESC, a.town.id", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(address -> addresses.append(String.format("%s, %s - %d employees%n",
                        address.getText(), address.getTown().getName(), address.getEmployees().size())));

        entityManager.close();
        entityManagerFactory.close();

        System.out.println(addresses.toString().trim());
    }
}
