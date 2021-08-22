package com.abumaster.example.springworkredis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * 启动类
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
@SpringBootApplication
public class SpringbootworkRedisApplication implements CommandLineRunner {

    @Resource
    private Environment environment;

    @Value("${spring.application.name}")
    private String name;
    public static void main(String[] args) {

        SpringApplication.run(SpringbootworkRedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(environment.getProperty("spring.application.name"));
    }
}
