package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

public class Main {

    public static void main(String[] args) {
//        ProductRepository productRepository = new ProductRepositoryImpl();
//        ProductService productService = new ProductService(productRepository);

        // IOC container
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ProductService productService = context.getBean("productService", ProductService.class);
        System.out.println("Product count: " + productService.count());
    }
}
