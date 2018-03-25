import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("lab");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User("Tosho", "Toshev", new Date());
        entityManager.persist(user);

        User found = entityManager.find(User.class, 1);
        found.setNotes("New notes");
        entityManager.persist(found);

        entityManager.detach(found);
        found.setDescription("some new description");
        entityManager.merge(found);

        found = entityManager.find(User.class, 1);
        System.out.println(found);
        entityManager.remove(found);

        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
