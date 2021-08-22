package com.abumaster.middleware.rmdb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.ServiceLoader;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserServiceTest {


    @Test
    public void spiUserTest() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "123456");
        if (null != connection) {
            System.out.println("=======> 数据库连接成功!");
        }

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver : " + driver.getClass()+";loader : "+serviceLoader.getClass().getClassLoader());
        }
        System.out.println("当前线程上线文类加载器 : " + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器 : " + ServiceLoader.class.getClassLoader());

    }


}