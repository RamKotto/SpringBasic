package ru.saraev;


import ru.saraev.conection_utils.Connection;
import ru.saraev.entity.Customer;
import ru.saraev.entity.Product;
import ru.saraev.persist.CustomerRepositoryImpl;
import ru.saraev.persist.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl(Connection.getOrCreateConnection());

        Customer customerOne = new Customer(null, "Dmitriy");

        customerRepository.save(customerOne);

        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);

        Customer secondCustomer = new Customer(null, "Maria");
        customerRepository.save(secondCustomer);
        customers = customerRepository.findAll();
        System.out.println(customers);

        Customer customerForDel = customerRepository.findById(customers.get(1).getId()).get();
        System.out.println(customerForDel);
        customerRepository.delete(customerForDel.getId());
        customers = customerRepository.findAll();
        System.out.println(customers);

        customerRepository.save(secondCustomer);



        Product productOne = new Product(null, "Milk", BigDecimal.valueOf(111.21));
        Product productTwo = new Product(null, "Water", BigDecimal.valueOf(31.90));
        Product productThree = new Product(null, "Cola", BigDecimal.valueOf(12.20));

        ProductRepositoryImpl productRepository = new ProductRepositoryImpl(Connection.getOrCreateConnection());

        productRepository.save(productOne);
        productRepository.save(productTwo);
        productRepository.save(productThree);

        List<Product> products = productRepository.findAll();
        System.out.println(products);

        customerOne = customerRepository.findById(1L).get();
        customerRepository.addProduct(customerOne, products);
        System.out.println(products);
        System.out.println(secondCustomer);
    }
}
