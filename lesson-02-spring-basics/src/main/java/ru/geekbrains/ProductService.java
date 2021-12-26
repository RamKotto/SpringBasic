package ru.geekbrains;

import ru.geekbrains.persist.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public long count() {
        return productRepository.findAll().size();
    }
}
