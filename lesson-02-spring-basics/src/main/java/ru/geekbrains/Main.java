package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;
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

//        cartServiceOne.putProductById(1);
//        cartServiceOne.putProductById(2);
//        cartServiceTwo.putProductById(3);
//        cartServiceTwo.putProductById(4);
//        cartServiceTwo.putProductById(5);
//
//        for (Product product : cartServiceOne.getCart()) {
//            System.out.println("Cart one: " + product.getName());
//        }
//
//        for (Product product : cartServiceTwo.getCart()) {
//            System.out.println("Cart two: " + product.getName());
//        }
//
//        cartServiceOne.removeFromCartById(1);
//        cartServiceOne.removeFromCartById(2);
//        cartServiceTwo.removeFromCartById(3);
//        cartServiceTwo.removeFromCartById(4);
//        cartServiceTwo.removeFromCartById(5);
//
//        if (cartServiceOne.getCart().size() <= 0) {
//            System.out.println("cartServiceOne is empty");
//        }
//
//        if (cartServiceOne.getCart().size() <= 0) {
//            System.out.println("cartServiceTwo is empty");
//        }
    }
}
