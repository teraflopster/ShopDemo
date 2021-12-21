package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public interface ShopController {

    @GetMapping("/product")
    String findAllProducts(Model model);

    @GetMapping("/product/new")
    String createProduct(Model model);

    @GetMapping("/product/{id}")
    String findProductById(@PathVariable int id, Model model);

    @GetMapping("/product/type/{type}")
    List<Product> findProductByType(@PathVariable String type);

    @PostMapping("/product")
    String save(@RequestParam MultipartFile image, @RequestParam String name, @RequestParam String type
                ,@RequestParam String desc,  @RequestParam long cost) throws IOException, NoSuchAlgorithmException;

    @DeleteMapping("/product/")
    void deleteProduct(@RequestBody Product product);
}
