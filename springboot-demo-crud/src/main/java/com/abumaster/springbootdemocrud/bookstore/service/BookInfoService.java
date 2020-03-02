package com.abumaster.springbootdemocrud.bookstore.service;

import com.abumaster.springbootdemocrud.bookstore.domain.BookInfo;

import java.util.List;

public interface BookInfoService{


    int deleteByPrimaryKey(String bid);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    List<BookInfo> queryBookInfoList();

}
