package com.abumaster.springbootdemocrud.bookstore.domain;

import lombok.Data;

@Data
public class User {
    private String uid;

    private String createTime;

    private String email;

    private String passWord;

    private String updateTime;

    private String userName;
}