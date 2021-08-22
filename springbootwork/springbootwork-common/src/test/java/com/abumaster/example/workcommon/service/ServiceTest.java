package com.abumaster.example.workcommon.service;

import com.abumaster.myself.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 简单的测试类
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Resource
    private AggregateService aggregateService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testOne() {
        aggregateService.queryOne();
    }

    @Test
    public void testTwo() {
        authorService.printAuthor();
    }
}
