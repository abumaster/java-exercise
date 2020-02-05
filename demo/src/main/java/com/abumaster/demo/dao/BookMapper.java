package com.abumaster.demo.dao;

import com.abumaster.demo.entity.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * @program: demo
 * @description: book的持久层
 * @author: zhang guofeng
 * @create: 2020-02-02 15:55
 */
public interface BookMapper extends CrudRepository<Book,Integer> {
}