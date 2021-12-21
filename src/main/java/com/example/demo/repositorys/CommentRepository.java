package com.example.demo.repositorys;

import com.example.demo.models.Comment;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, ID> {
    List<Comment> findAllByProductId(long id);
}
