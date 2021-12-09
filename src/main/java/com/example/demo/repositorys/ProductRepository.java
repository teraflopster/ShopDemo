package com.example.demo.repositorys;

import com.example.demo.models.Product;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, ID> {
    List<Product> findAllByName(String name);

    List<Product> findAllByType(String type);
    List<Product> findAllByCost(long cost);

    @Modifying
    @Query("UPDATE Product AS product SET product.name = ?1, product.description = ?2," +
            " product.cost = ?3, product.type = ?4, product.image = ?5 where product.id = ?6")
    void updateProduct(String name, String description, long cost, String type, String image, long id);

    Optional<Product> findById(long id);
}
