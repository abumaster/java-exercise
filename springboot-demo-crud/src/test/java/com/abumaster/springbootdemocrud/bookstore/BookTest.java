package com.abumaster.springbootdemocrud.bookstore;

import com.abumaster.springbootdemocrud.bookstore.domain.BookInfo;
import com.abumaster.springbootdemocrud.bookstore.service.BookInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.abumaster.springbootdemocrud.bookstore.mapper")
public class BookTest {


    @Resource(name ="bookInfoServiceImpl" )
    private BookInfoService bookInfoService;

    @Test
    public void addOneBook(){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBid(DigestUtils.md5DigestAsHex("十万个为什么".getBytes()));
        bookInfo.setBookName("十万个为什么");
        bookInfo.setBookDesc("十万个为什么");
        bookInfo.setAuthor("sda");
        bookInfo.setUpdateTime("123");
        bookInfo.setDetails("十万个为什么十万个为什么十万个为什么");
        bookInfoService.insert(bookInfo);

    }
}
