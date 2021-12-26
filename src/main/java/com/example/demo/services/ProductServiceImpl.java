package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositorys.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProduct(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findProductByType(String type) {
        return productRepository.findAllByType(type);
    }

    @Override
    public List<Product> findAllProductByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findAllProductByCost(long cost) {
        return productRepository.findAllByCost(cost);
    }

    @Override
    public void updateProduct(Product product, long id) {
        productRepository.updateProduct(product.getName(), product.getDescription(),
                product.getCost(), product.getType(), product.getImage(), product.getId());
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
