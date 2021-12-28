package com.example.demo.repositorys;

import com.example.demo.models.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ID> {
    Optional<User> findByEmail(String email);
}
