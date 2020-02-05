package com.abumaster.demo.service.impl;

import com.abumaster.demo.dao.BookMapper;
import com.abumaster.demo.entity.Book;
import com.abumaster.demo.service.BookStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 书籍服务的实现类
 * @author: zhang guofeng
 * @create: 2020-02-02 16:03
 */
@Service
@Slf4j
public class BookStoreServiceImpl implements BookStoreService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteById(id);
    }

    @Override
    public void editBook(Book book) {
        bookMapper.save(book);
    }

    @Override
    public List<Book> listAllBook() {
        return (List<Book>) bookMapper.findAll();
    }

    @Override
    public Book queryBook(Integer id) {
        return bookMapper.findById(id).orElse(null);
    }
}