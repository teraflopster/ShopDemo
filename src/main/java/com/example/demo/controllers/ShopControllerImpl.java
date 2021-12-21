package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.Product;
import com.example.demo.services.CommentServiceImpl;
import com.example.demo.services.MinioService;
import com.example.demo.services.MinioServiceImpl;
import com.example.demo.services.ProductServiceImpl;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ShopControllerImpl implements ShopController{

    private final ProductServiceImpl productService;

    private final CommentServiceImpl commentService;

    private final MinioServiceImpl minioService;

    @Autowired
    ShopControllerImpl(ProductServiceImpl productService, CommentServiceImpl commentService, MinioServiceImpl minioService) {
        this.productService = productService;
        this.commentService = commentService;
        this.minioService = minioService;
    }



    @Override
    public String findAllProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        for (Product product : products) {
            product.setImage(minioService.getUrl(product.getImage()));
        }
        model.addAttribute("products", products.stream().toArray());
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
        List<Comment> comments = commentService.findAllByProductId(product.get().getId());
        model.addAttribute("product", product.stream().toArray());
        model.addAttribute("comments", comments.stream().toArray());
        return "/product";
    }

    @Override
    public List<Product> findProductByType(@PathVariable String type) {
        return productService.findStudentsByType(type);
    }


    /* POST /product */
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
}
