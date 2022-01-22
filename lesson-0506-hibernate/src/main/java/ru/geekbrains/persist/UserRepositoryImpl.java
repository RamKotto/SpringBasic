package ru.geekbrains.persist;

import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {

    // синглтоновый объкект, потокобезопасный
    private final EntityManagerFactory entityManagerFactory;

    public UserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> findAll() {
        return executeForEntityManager(
                entityManager -> entityManager.createQuery("select u from User u", User.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<User> findById(long id) {
        return executeForEntityManager(
                entityManager -> Optional.ofNullable(entityManager.find(User.class, id))
        );
    }

    @Override
    public void save(User user) {
        executeInTransaction(entityManager -> {
            if (user.getId() == null) {
                entityManager.persist(user);
            } else {
                entityManager.merge(user);
            }
        });
    }

    @Override
    public void delete(long id) {
        executeInTransaction(
                entityManager -> entityManager.createQuery("delete from User where id = :id")
                        .setParameter("id", id)
                        .executeUpdate());
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
