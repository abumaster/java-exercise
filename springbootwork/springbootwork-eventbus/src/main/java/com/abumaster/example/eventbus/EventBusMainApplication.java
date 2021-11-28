package com.abumaster.example.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主函数
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@SpringBootApplication
@Slf4j
public class EventBusMainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(EventBusMainApplication.class, args);


    }
}
