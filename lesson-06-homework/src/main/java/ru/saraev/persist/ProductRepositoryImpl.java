package ru.saraev.persist;

import ru.saraev.conection_utils.Connection;
import ru.saraev.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final Connection connection;

    public ProductRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() {
        return connection.executeForEntityManager(
                entityManager -> entityManager.createQuery("select p from Product p", Product.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<Product> findById(long id) {
        return connection.executeForEntityManager(
                entityManager -> Optional.ofNullable(entityManager.find(Product.class, id))
        );
    }

    @Override
    public void save(Product product) {
        connection.executeInTransaction(entityManager -> {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }

    @Override
    public void delete(long id) {
        connection.executeInTransaction(
                entityManager -> entityManager.createQuery("delete from Product where id = :id")
                        .setParameter("id", id)
                        .executeUpdate());
    }
}
