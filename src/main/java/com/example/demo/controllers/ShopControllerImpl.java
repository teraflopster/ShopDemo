package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ShopControllerImpl implements ShopController{




    private ProductServiceImpl productService;

    @Autowired
    ShopControllerImpl(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @Override
    public Optional<Product> findProductById(@PathVariable int id) {
        return productService.findProduct(id);
    }

    @Override
    public List<Product> findProductByType(@PathVariable String type) {
        return productService.findStudentsByType(type);
    }

    @Override
    public Product save(MultipartFile image, String name, String type, String desc, long cost) {
        Product product = new Product("image", name, type,desc,cost);
        productService.saveProduct(product);
        return product;
    }

    @Override
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }
}
