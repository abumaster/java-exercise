package com.abumaster.springbootdemocrud.bookstore.domain;

import lombok.Data;

@Data
public class BookInfo {
    /**
     * bid
     */
    private String bid;

    /**
     * bookName
     */
    private String bookName;

    /**
     * bookDesc
     */
    private String bookDesc;

    /**
     * author
     */
    private String author;

    /**
     * updateTime
     */
    private String updateTime;

    /**
     * details
     */
    private String details;
}