package ru.saraev.persist;

import ru.saraev.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(long id);

    void save(Customer customer);

    void delete(long id);
}
