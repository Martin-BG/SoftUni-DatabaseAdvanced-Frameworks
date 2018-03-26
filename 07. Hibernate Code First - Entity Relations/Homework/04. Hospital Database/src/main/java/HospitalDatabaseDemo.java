import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;

public class HospitalDatabaseDemo {

    public static void main(String[] args) {

        Medicament medicament = new Medicament();
        medicament.setName("Drug");

        Medicament medicament2 = new Medicament();
        medicament2.setName("Stronger drug");

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Mindless");
        diagnose.setComments("Just joking :)");

        Diagnose diagnose2 = new Diagnose();
        diagnose2.setName("Diagnose 2");
        diagnose2.setComments("hohoho");

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setComments("Just visiting...");

        Visitation visitation2 = new Visitation();
        visitation2.setDate(new Date());
        visitation2.setComments("Ole!");

        Visitation visitation3 = new Visitation();
        visitation3.setDate(new Date());
        visitation3.setComments("A cat");

        Patient patient = new Patient();
        patient.setFirstName("Pesho");
        patient.setLastName("Peshev");
        patient.setBirthDate(new Date());
        patient.setAddress("Sofia, Mladost");
        patient.setHasMedicalInsurance(true);
        patient.setEmail("peshoo@abv.bg");
        patient.setPicture(new byte[8192]);
        patient.setDiagnoses(new HashSet<Diagnose>() {{
            add(diagnose);
            add(diagnose2);
        }});
        patient.setPrescriptions(new HashSet<Medicament>() {{
            add(medicament);
            add(medicament2);
        }});
        patient.setVisitations(new HashSet<Visitation>() {{
            add(visitation);
            add(visitation2);
        }});

        Patient patient2 = new Patient();
        patient2.setFirstName("Dodo");
        patient2.setLastName("Dushev");
        patient2.setBirthDate(new Date());
        patient2.setAddress("Sofia, Lyulin");
        patient2.setHasMedicalInsurance(false);
        patient2.setEmail("dodo@abv.bg");
        patient2.setPicture(new byte[50000]);
        patient2.setDiagnoses(new HashSet<Diagnose>() {{
            add(diagnose);
            add(diagnose2);
        }});
        patient2.setPrescriptions(new HashSet<Medicament>() {{
            add(medicament);
            add(medicament2);
        }});
        patient2.setVisitations(new HashSet<Visitation>() {{
            add(visitation3);
        }});

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(medicament);
            entityManager.persist(medicament2);
            entityManager.persist(diagnose);
            entityManager.persist(diagnose2);
            entityManager.persist(visitation);
            entityManager.persist(visitation2);
            entityManager.persist(visitation3);
            entityManager.persist(patient);
            entityManager.persist(patient2);

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
