package ru.geekbrains;

import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductService(productRepository);
        System.out.println("Product count: " + productService.count());
    }
}
