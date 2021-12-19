package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public interface ShopController {
    @GetMapping(    "/product")
    List<Product> findAllProducts();

    @GetMapping("/product/{id}")
    Optional<Product> findProductById(@PathVariable int id);

    @GetMapping("/product/type/{type}")
    List<Product> findProductByType(@PathVariable String type);

    @PostMapping("/product")
    Product save(@RequestParam MultipartFile image, @RequestParam String name, @RequestParam String type
                ,@RequestParam String desc,  @RequestParam long cost) throws IOException, NoSuchAlgorithmException;

    @DeleteMapping("/product/")
    void deleteProduct(@RequestBody Product product);
}
