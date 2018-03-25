import entities.Course;
import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;

public class UniversitySystemDemo {

    public static void main(String[] args) {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Mr.");
        teacher.setLastName("Johnes");
        teacher.setEmail("jj@gmail.com");

        Student student = new Student();
        student.setFirstName("Pesho");
        student.setLastName("Peshev");
        student.setCourses(new HashSet<>());

        Course course = new Course();
        course.setName("Java");
        course.setDescription("rulezzz");
        course.setStartDate(new Date());
        course.setTeacher(teacher);
        course.setStudents(new HashSet<>());

        course.getStudents().add(student);

        student.getCourses().add(course);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("university");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(teacher);
            entityManager.persist(student);
            entityManager.persist(course);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
