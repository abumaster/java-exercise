package com.abumaster.springbootdemocrud.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.abumaster.springbootdemocrud.bookstore.mapper.BookInfoMapper;
import com.abumaster.springbootdemocrud.bookstore.domain.BookInfo;
import com.abumaster.springbootdemocrud.bookstore.service.BookInfoService;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService{

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public int deleteByPrimaryKey(String bid) {
        return bookInfoMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(BookInfo record) {
        return bookInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(BookInfo record) {
        return bookInfoMapper.insertSelective(record);
    }

    @Override
    public BookInfo selectByPrimaryKey(String bid) {
        return bookInfoMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BookInfo> queryBookInfoList() {
        return bookInfoMapper.queryAllBook();
    }
}
