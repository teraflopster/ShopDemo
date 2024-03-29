package com.example.demo.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Slf4j
@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {
  
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "cost")
    private long cost;

    public Product() {

    }

    public Product(String image, String name, String type, String description, long cost) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
    }
}