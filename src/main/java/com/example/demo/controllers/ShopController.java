package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/products")
public interface ShopController {
    @GetMapping("/get/all")
    List<Product> findAllProducts();

    @GetMapping("/get/by/id/{id}")
    Product findProductById(@PathVariable int id);

    @GetMapping("/get/by/type/{type}")
    List<Product> findProductByType(@PathVariable String type);

    @PostMapping("/add/product")
    Product save(@RequestParam MultipartFile file, @RequestParam String name,
                 @RequestParam int cost, @RequestParam String desc) throws IOException, NoSuchAlgorithmException;

    @DeleteMapping("/delete/by/id")
    void deleteProductById();
}
