package com.example.demo.services;

import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    void saveProduct(Product product);

    List<Product> findAllProducts();

    Optional<Product> findProduct(long id);

    List<Product> findProductByType(String type);

    List<Product> findAllProductByName(String name);

    List<Product> findAllProductByCost(long cost);

    void updateProduct(Product product, long id);

    void deleteProduct(Product product);
}
