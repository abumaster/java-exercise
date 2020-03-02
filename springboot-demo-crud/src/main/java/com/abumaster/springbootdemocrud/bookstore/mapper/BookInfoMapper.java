package com.abumaster.springbootdemocrud.bookstore.mapper;

import com.abumaster.springbootdemocrud.bookstore.domain.BookInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookInfoMapper {
    int deleteByPrimaryKey(String bid);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
    List<BookInfo> queryAllBook();
}