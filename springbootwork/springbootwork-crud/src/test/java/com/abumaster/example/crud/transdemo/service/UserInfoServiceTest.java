package com.abumaster.example.crud.transdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoServiceTest {

    @Resource
    private UserInfoService userInfoService;

    @Test
    public void insertOneDemo() {
        userInfoService.insertOneDemo();
    }

    @Test
    public void insertException() {
        userInfoService.insertWithException();
    }
    @Test
    public void testDemo1() {
        userInfoService.insertNoTransactionalRequiredNew();
    }
    @Test
    public void testDemo2() {
        userInfoService.insertNoTransactionalRequiredNewTwo();
    }
    @Test
    public void testDemo3() {
        userInfoService.insertTransactionalRequiredNew();
    }

    @Test
    public void testDemo4() {
        userInfoService.insertTransactionalRequiredNewTwo();
    }
    @Test
    public void testDemo5() {
        userInfoService.insertTransactionalRequiredNewThree();
    }


}