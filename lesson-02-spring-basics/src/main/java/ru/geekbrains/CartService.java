package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<Product> productCart = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public void putProductById(long id) {
        productCart.add(productRepository.findById(id));
    }

    public List<Product> getCart() {
        return productCart;
    }

    public void removeFromCartById(long id) {
        productCart.remove(productRepository.findById(id));
    }
}
