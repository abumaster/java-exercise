package com.abumaster.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: demo
 * @description: 书籍实体
 * @author: zhang guofeng
 * @create: 2020-02-02 15:47
 */
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue
    private Integer bid;
    private String bookName;
    private String isbn;
    @Column(name = "bdesc")
    private String desc;
    @Column(name="bauthor")
    private String author;
    private String price;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}