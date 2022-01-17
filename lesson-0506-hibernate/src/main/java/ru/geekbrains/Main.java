package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // потокобезопасный
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // не потокобезопасный
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // INSERT
//        entityManager.getTransaction().begin();
//
//        // внесение данных в таблицу
//        entityManager.persist(new User(null, "Maria", "pass"));
//
//        // закрываем транзакцию
//        entityManager.getTransaction().commit();


        // SELECT
//        System.out.println("User:");
//        User user = entityManager.find(User.class, 1L);
//        System.out.println(user);
//
//        System.out.println("Users:");
//        List<User> users =  entityManager.createQuery("select u from User u where u.username = :username", User.class)
//                .setParameter("username", "Maria")
//                .getResultList();
//        System.out.println(users);
//
//        users = entityManager.createNativeQuery("select * from users;", User.class)
//                .getResultList();
//        System.out.println(users);


        // UPDATE 1
//        User user = entityManager.find(User.class, 1L);
//        System.out.println(user);
//        entityManager.getTransaction().begin();
//        user.setPassword("NewPassword");
//        entityManager.getTransaction().commit();
//        user = entityManager.find(User.class, 1L);
//        System.out.println(user);

        // UPDATE 2
//        User user = new User(1L, "Mike", "passwordForMike");
//        entityManager.getTransaction().begin();
//        entityManager.merge(user);
//        entityManager.getTransaction().commit();
//        user = entityManager.find(User.class, 1L);
//        System.out.println(user);

        // UPDATE 3
//        entityManager.getTransaction().begin();
//        entityManager.createQuery("update User set username = :username, password = :password where id = :id")
//                .setParameter("username", "Dmitriy")
//                .setParameter("password", "dmitriy123")
//                .setParameter("id", 1L)
//                .executeUpdate();
//
//        entityManager.getTransaction().commit();


        // DELETE 1
//        entityManager.getTransaction().begin();
//        User user = entityManager.find(User.class, 1L);
//        entityManager.remove(user);
//        entityManager.getTransaction().commit();

        // DELETE 2
        entityManager.getTransaction().begin();

        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", 1L)
                .executeUpdate();

        entityManager.getTransaction().commit();


        entityManager.close();
    }
}
