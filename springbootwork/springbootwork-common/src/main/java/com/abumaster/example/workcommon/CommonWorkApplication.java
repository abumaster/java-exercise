package com.abumaster.example.workcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主函数
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/31
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class CommonWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonWorkApplication.class, args);
    }
}
