package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.User;
import ru.geekbrains.persist.UserRepositoryImpl;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainUserHibernate {

    public static void main(String[] args) {

        User user = new User(null, "Dmitriy", "myPassword");

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        UserRepositoryImpl userRepository = new UserRepositoryImpl(entityManagerFactory);

        userRepository.save(user);
        List<User> users = userRepository.findAll();
        System.out.println(users);
    }
}
