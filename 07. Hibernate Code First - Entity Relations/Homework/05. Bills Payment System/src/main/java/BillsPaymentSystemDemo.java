import entities.BankAccount;
import entities.CreditCard;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class BillsPaymentSystemDemo {

    public static void main(String[] args) {

        User user1 = new User();
        user1.setEmail("toto@abv.bg");
        user1.setFirstName("Pesho");
        user1.setLastName("Peshev");
        user1.setPassword("1234");
        user1.setBillingDetails(new HashSet<>());

        User user2 = new User();
        user2.setEmail("gogo@abv.bg");
        user2.setFirstName("Gosho");
        user2.setLastName("Geshev");
        user2.setPassword("5678");
        user2.setBillingDetails(new HashSet<>());

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setName("Bank 1");
        bankAccount1.setSwiftCode("Swift 1");
        bankAccount1.setNumber("1111");
        bankAccount1.setOwner(user1);

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setName("Bank 2");
        bankAccount2.setSwiftCode("Swift 2");
        bankAccount2.setNumber("2222");
        bankAccount2.setOwner(user2);

        CreditCard creditCard1 = new CreditCard();
        creditCard1.setCardType("Card 1");
        creditCard1.setExpirationMonth((byte) 1);
        creditCard1.setExpirationYear((short) 2011);
        creditCard1.setNumber("Card1111");
        creditCard1.setOwner(user1);

        CreditCard creditCard2 = new CreditCard();
        creditCard2.setCardType("Card 2");
        creditCard2.setExpirationMonth((byte) 2);
        creditCard2.setExpirationYear((short) 2022);
        creditCard2.setNumber("Card2222");
        creditCard2.setOwner(user2);

        user1.getBillingDetails().add(bankAccount1);
        user2.getBillingDetails().add(bankAccount2);
        user1.getBillingDetails().add(creditCard1);
        user2.getBillingDetails().add(creditCard2);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bills_payment_system");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(user1);
            entityManager.persist(user2);

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
