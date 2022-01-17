package ru.geekbrains.persist;

import ru.geekbrains.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(long id);

    Product saveOrUpdate(Product product);

    void deleteById(long id);
}
