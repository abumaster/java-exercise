package com.abumaster.springbootdemocrud.bookstore.mapper;

import com.abumaster.springbootdemocrud.bookstore.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKey(User record);

    User selectOneByUserName(String userName);

    User selectOneByUserNameAndPassWord(String userName,String password);
}