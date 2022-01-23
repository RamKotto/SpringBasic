package ru.saraev.conection_utils;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public class Connection {
    private static Connection connection;
    private final EntityManagerFactory entityManagerFactory;

    private Connection() {
        this.entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Connection getOrCreateConnection() {
        if (connection == null) {
            connection = new Connection();
            return connection;
        }
        return connection;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
