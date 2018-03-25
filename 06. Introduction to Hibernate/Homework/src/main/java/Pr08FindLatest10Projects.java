import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class Pr08FindLatest10Projects {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder projects = new StringBuilder();

        entityManager
                .createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> projects
                        .append("Project name: ").append(project.getName()).append(System.lineSeparator())
                        .append("\tProject Description: ").append(project.getDescription()).append(System.lineSeparator())
                        .append("\tProject Start Date: ").append(project.getStartDate()).append(System.lineSeparator())
                        .append("\tProject End Date: ").append(project.getEndDate()).append(System.lineSeparator()));

        entityManager.close();
        entityManagerFactory.close();

        System.out.println(projects.toString().trim());
    }
}
