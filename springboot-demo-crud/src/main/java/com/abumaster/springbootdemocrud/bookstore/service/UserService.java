package com.abumaster.springbootdemocrud.bookstore.service;

import com.abumaster.springbootdemocrud.bookstore.domain.User;
public interface UserService{


    int deleteByPrimaryKey(String uid);

    int insert(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKey(User record);

    int checkUserName(String userName);

    int checkUserNameAndPassword(String userName,String password);

    User getOneUserByName(String userName);
}
