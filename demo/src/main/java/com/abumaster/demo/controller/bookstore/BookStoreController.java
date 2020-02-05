package com.abumaster.demo.controller.bookstore;

import com.abumaster.demo.entity.Book;
import com.abumaster.demo.service.BookStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description: 图书管理Controller层
 * @author: zhang guofeng
 * @create: 2020-02-02 15:51
 */
@Controller
@Slf4j
@RequestMapping("/bookstore")
public class BookStoreController {
    @Resource
    BookStoreService bookStoreService;

    /**
     * 首页
     * @param model model
     * @return 首页的URL
     */
    @RequestMapping
    public String index(Model model){
        List<Book> books=bookStoreService.listAllBook();
        model.addAttribute("books",books);
        return "index";
    }

}