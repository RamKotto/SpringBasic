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
//        User user = new User(null, "MrTwister", "mySecurePassword");
//        entityManager.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact(null, "home phone", "1234567", user));
//        contacts.add(new Contact(null, "work phone", "7654321", user));
//        contacts.add(new Contact(null, "mobile phone", "321456987", user));
//        contacts.add(new Contact(null, "email", "superman@justice.com", user));
//        contacts.forEach(entityManager::persist);
//
//        entityManager.getTransaction().commit();

        // INSERT 2
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, 1L);
        user.getContacts().add(
                new Contact(null, "Home address", "New-York, St-Pier street, 123/2", user)
        );
        entityManager.merge(user);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
