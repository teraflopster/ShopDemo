package com.example.demo.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Data
@Entity
@Table(name = "goods")
public class Product implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private int cost;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    public Product() {

    }

    public Product(int cost, String name, String description, String image) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.image = image;
    }

}