package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Contact;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main06 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // INSERT 1
//        entityManager.getTransaction().begin();
//
//        User user = new User(null, "Luntik", "mySecurePassword");
//        entityManager.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact(null, "home phone", "7777777", user));
//        contacts.add(new Contact(null, "work phone", "6666666", user));
//        contacts.add(new Contact(null, "mobile phone", "9999999999", user));
//        contacts.add(new Contact(null, "email", "luntik@fromtv.com", user));
//        contacts.forEach(entityManager::persist);
//
//        entityManager.getTransaction().commit();

        // INSERT 2 (need cascade)
//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, 1L);
//        user.getContacts().add(
//                new Contact(null, "Home phone", "5955246", user)
//        );
//        entityManager.merge(user);
//
//        entityManager.getTransaction().commit();

        // SELECT 1
//        List<User> users = entityManager.createQuery("" +
//                        "select distinct u " +
//                        "from User u " +
//                        "inner join u.contacts " +
//                        "where u.id = :id", User.class)
//                .setParameter("id", 2L)
//                .getResultList();
//        System.out.println(users);

        // SELECT 2 (n+1 problem) must use fetch in query
//        List<Contact> contacts = entityManager.createQuery("" +
//                        "select distinct c " +
//                        "from Contact c " +
//                        "join fetch c.user " +    // fetch - для решения проблемы "n + 1"
//                        "where c.type = :type", Contact.class)
//                .setParameter("type", "mobile phone")
//                .getResultList();
//
//        contacts.forEach(System.out::println);

        // DELETE 1
//        entityManager.getTransaction().begin();
//
//        entityManager.createQuery("delete from Contact c where c.id = :id")
//                .setParameter("id", 1L)
//                .executeUpdate();
//
//        entityManager.getTransaction().commit();

        // DELETE 2 без   orphanRemoval = true    работать не будет! см User.class
//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, 1L);
//        user.getContacts().remove(0);
//        entityManager.merge(user);
//
//        entityManager.getTransaction().commit();

        // DELETE 3 - cascade delete см User.class. Если мы хотим удалить целого пользователя.
//        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, 1L);
//        entityManager.remove(user);
//
//        entityManager.getTransaction().commit();

        entityManager.close();

    }
}
