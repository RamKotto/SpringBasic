package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private Map<Product, Integer> productCount;

    private ProductRepository productRepository;

    @Autowired
    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productCount = new HashMap<>();
    }

    public void addProduct(long id, int count) {
        Product product = getProductById(id);
        productCount.merge(product, count, Integer::sum);
    }

    public void removeProduct(long id, int count) {
        Product product = getProductById(id);
        Integer curr = productCount.get(product);
        if (curr <= count) {
            productCount.remove(product);
        } else {
            productCount.merge(product, -count, Integer::sum);
        }
    }

    public List<Product> getAll() {
        return new ArrayList<>(productCount.keySet());
    }

    private Product getProductById(long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Prodict with id:" + id + " is not exists");
        }
        return product;
    }
}
