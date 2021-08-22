package com.abumaster.example.workconfig;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 主函数
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/20
 */
@SpringBootApplication
@EnableAsync
@Slf4j
public class UseApolloApplication implements CommandLineRunner {

    @Value("${user.name}")
    private String userName;

    @Value(
            "${content}"
    )
    private String content;

    @Override
    public void run(String... args) throws Exception {
        log.info("读取配置:{}, {}", userName, content);
    }

    public static void main(String[] args) {
        SpringApplication.run(UseApolloApplication.class, args);
    }
}
