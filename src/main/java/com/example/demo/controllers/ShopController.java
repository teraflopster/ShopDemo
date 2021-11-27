package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public interface ShopController {
    @GetMapping("/get/all")
    List<Product> findAllProducts();

    @PostMapping("/add/product")
    Product save(@RequestBody Product product);
}
