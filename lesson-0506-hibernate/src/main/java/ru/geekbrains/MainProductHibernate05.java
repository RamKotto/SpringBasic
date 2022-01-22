package ru.geekbrains;

import ru.geekbrains.entity.Product;
import ru.geekbrains.persist.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

public class MainProductHibernate05 {
    public static void main(String[] args) {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        productRepository.init();

        Product productOne = new Product(null,
                "aaa", "Mi", new BigDecimal("123.4444"));
        Product createdProd = productRepository.saveOrUpdate(productOne);

        productRepository.deleteById(createdProd.getId());

        Product productForMerge = productRepository.findById(1L);
        productForMerge.setName("MyCustomName");
        productRepository.saveOrUpdate(productForMerge);

        List<Product> products = productRepository.findAll();
        System.out.println(products);

        Product product = productRepository.findById(1L);
        System.out.println(product);
    }
}
