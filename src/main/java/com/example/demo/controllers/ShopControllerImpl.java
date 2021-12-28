package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.CommentServiceImpl;
import com.example.demo.services.MinioServiceImpl;
import com.example.demo.services.ProductServiceImpl;
import com.example.demo.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ShopControllerImpl implements ShopController {

    private final ProductServiceImpl productService;

    private final CommentServiceImpl commentService;

    private final MinioServiceImpl minioService;

    private final UserServiceImpl userService;

    @Autowired
    ShopControllerImpl(ProductServiceImpl productService, CommentServiceImpl commentService,
                       MinioServiceImpl minioService, UserServiceImpl userService) {
        this.productService = productService;
        this.commentService = commentService;
        this.minioService = minioService;
        this.userService = userService;
    }

    @Override
    public String findAllProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        for (Product product : products) {
            product.setImage(minioService.getUrl(product.getImage()));
        }
        model.addAttribute("products", products.toArray());
        return "/products";
    }

    @Override
    public String createProduct(Model model) {
        model.addAttribute("some", "this is somethings");
        return "new";
    }

    @Override
    public String findProductById(@PathVariable int id, Model model) {
        Optional<Product> product = productService.findProduct(id);
        product.ifPresent(value -> value.setImage(minioService.getUrl(value.getImage())));
        List<Comment> comments = commentService.findAllByProductId(id);
        model.addAttribute("product", product.stream().toArray());
        model.addAttribute("comments", comments.toArray());
        return "/product";
    }

    @Override
    public String findProductByType(@PathVariable String type, Model model) {
        List<Product> products = productService.findProductByType(type);
        for (Product product : products) {
            product.setImage(minioService.getUrl(product.getImage()));
        }
        model.addAttribute("products", products.toArray());
        return "/products";
    }

    @Override
    public String save(MultipartFile image, String name, String type, String desc, long cost) {
        Product product = new Product(minioService.saveImage(image), name, type,desc,cost);
        productService.saveProduct(product);
        return "redirect:/";
    }

    @Override
    public void deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
    }

    @Override
    public String getLoginPage() {
        return "/login";
    }


    // /api/new/user
    @Override
    public String saveNewUser(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()).isEmpty()) {
            user.setRole(Role.USER);
            user.setStatus(Status.ACTIVE);
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userService.saveUser(user);
            log.info("User saves: {}", user);
        } else {
            log.info("User not saves: {}, email is in use by another user!", user);
        }
        return "redirect:/";
    }

    @Override
    public String getAdminPage(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "/admin";
    }

    @Override
    public String getRegistration() {
        return "/registration";
    }

    private PasswordEncoder passwordEncoder( ) {return new BCryptPasswordEncoder(12);}
}
