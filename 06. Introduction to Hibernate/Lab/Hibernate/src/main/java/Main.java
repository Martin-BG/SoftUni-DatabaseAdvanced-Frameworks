import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student student1 = new Student();
        student1.setName("Simona");
        student1.setBirthDate(new Date());

        session.persist(student1);

        student1.setName("Dodo");

        System.out.println(session.get(Student.class, 1).getName());

        Student student2 = new Student("Koko", new Date());
        Student student3 = new Student("Toto", new Date());
        Student student4 = new Student("Toto2", new Date());

        session.persist(student2);
        session.persist(student3);
        session.persist(student4);

        @SuppressWarnings("unchecked")
        List<Student> students = session.createQuery("FROM Student ").list();

        for (Student student : students) {
            System.out.println(student.getName());
        }

        @SuppressWarnings("unchecked")
        List<Student> students2 = session.createQuery("FROM Student WHERE name like 'T%'").list();
        for (Student student : students2) {
            System.out.println(student.getName());
        }

        session.getTransaction().commit();
        session.close();

    }
}
