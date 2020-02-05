package com.abumaster.demo.service;

import com.abumaster.demo.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description: book Service的测试
 * @author: zhang guofeng
 * @create: 2020-02-02 21:13
 */
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class BookServiceTest {
    @Resource
    BookStoreService bookStoreService;

    @Test
    public void testAdd(){
        log.info("测试图书的添加");
        for(int i=0;i<10;++i){
            Book book=new Book();
            book.setBookName("测试书籍名称"+i);
            book.setAuthor("作者:"+i);
            book.setDesc("乱七八兆的藐视+"+i);
            book.setPrice("$"+i*10+8);
            bookStoreService.addBook(book);
        }
        log.info("添加书籍完成");
        Assert.assertEquals(bookStoreService.listAllBook().size(),10);
    }
}