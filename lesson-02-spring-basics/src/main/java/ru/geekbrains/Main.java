package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.ls.LSOutput;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;

public class Main {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryImpl();
//        ProductService productService = new ProductService(productRepository);

        // IOC container
        // ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        CartService cartServiceOne = context.getBean("cartService", CartService.class);
        CartService cartServiceTwo = context.getBean("cartService", CartService.class);

        System.out.println("Cart name: " + cartServiceOne);
        System.out.println("Cart name: " + cartServiceTwo);

        System.out.println(productService.count());
    }
}
