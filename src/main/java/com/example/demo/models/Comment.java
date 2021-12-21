package com.example.demo.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private String date;

    @Column(name = "recommends")
    private boolean recommends;

//    public boolean isRecommends() {
//        return recommends;
//    }

    public Comment(long id, long productId, String comment, String author, String date, boolean recommends) {
        this.id = id;
        this.productId = productId;
        this.comment = comment;
        this.author = author;
        this.date = date;
        this.recommends = recommends;
    }

    public Comment() {

    }
}
