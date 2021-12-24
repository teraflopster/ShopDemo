package com.example.demo.controllers;

import com.example.demo.models.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/api")
public interface ShopController {

    @GetMapping("/product")
    String findAllProducts(Model model);

    @GetMapping("/product/new")
    @PreAuthorize("hasAuthority('admin:read')")
    String createProduct(Model model);

    @GetMapping("/product/{id}")
    String findProductById(@PathVariable int id, Model model);

    @GetMapping("/product/type/")
    String findProductByType(@RequestParam String type, Model model);

    @PostMapping("/product")
    @PreAuthorize("hasAuthority('admin:write')")
    String save(@RequestParam MultipartFile image, @RequestParam String name, @RequestParam String type
                ,@RequestParam String desc,  @RequestParam long cost) throws IOException, NoSuchAlgorithmException;

    @DeleteMapping("/product/")
    @PreAuthorize("hasAuthority('admin:write')")
    void deleteProduct(@RequestBody Product product);

    @GetMapping("/login")
    String getLoginPage();
}
