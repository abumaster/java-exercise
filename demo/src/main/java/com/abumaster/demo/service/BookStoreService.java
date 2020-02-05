package com.abumaster.demo.service;

import com.abumaster.demo.entity.Book;

import java.util.List;

/**
 * @description: book的服务层
 * @author: zhang guofeng
 * @create: 2020-02-02 15:59
 */
public interface BookStoreService {
    /**
     * 添加书籍
     * @param book book
     */
    void addBook(Book book);

    /**
     * 删除书籍
     * @param id 书籍标号
     */
    void deleteBook(Integer id);

    /**
     * 编辑书籍
     * @param book 书籍实体
     */
    void editBook(Book book);

    /**
     * 查询书单列表
     * @return list
     */
    List<Book> listAllBook();

    /**
     * 查看书籍的详细信息
     * @param id 书籍标号
     * @return 书籍实体
     */
    Book queryBook(Integer id);
}
