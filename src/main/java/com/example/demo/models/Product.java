package com.example.demo.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Data
@Entity
@Table(name = "products")
public class Product implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "cost")
    private long cost;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "image")
    private String image;

    public Product() {

    }

    public Product(long cost, String name, String description, String type, String image) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.type = type;
        this.image = image;
    }
}