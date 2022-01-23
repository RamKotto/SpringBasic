package ru.saraev.persist;

import ru.saraev.entity.Customer;
import ru.saraev.conection_utils.Connection;
import ru.saraev.entity.Product;

import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final Connection connection;

    public CustomerRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> findAll() {
        return connection.executeForEntityManager(
                entityManager -> entityManager.createQuery("select c from Customer c", Customer.class)
                        .getResultList()
        );
    }

    @Override
    public Optional<Customer> findById(long id) {
        return connection.executeForEntityManager(
                entityManager -> Optional.ofNullable(entityManager.find(Customer.class, id))
        );
    }

    @Override
    public void save(Customer customer) {
        connection.executeInTransaction(entityManager -> {
            if (customer.getId() == null) {
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        });
    }

    @Override
    public void delete(long id) {
        connection.executeInTransaction(
                entityManager -> entityManager.createQuery("delete from Customer where id = :id")
                        .setParameter("id", id)
                        .executeUpdate());
    }

    public List<Product> addProduct(Customer customer, List<Product> products) {
        connection.executeInTransaction(entityManager -> {
            for (Product product : products) {
                entityManager.createNativeQuery("" +
                                "insert into products_customers (products_id, customers_id) " +
                                "value (:productId, :customerId);")
                        .setParameter("productId", product.getId())
                        .setParameter("customerId", customer.getId())
                        .executeUpdate();
            }
        });
        return customer.getProducts();
    }
}
