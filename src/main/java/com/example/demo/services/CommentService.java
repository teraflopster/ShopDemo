package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> findAllByProductId(long id);
}
