package com.abumaster.springbootdemocrud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.abumaster.springbootdemocrud.bookstore.mapper")
@EnableSwagger2
public class SpringbootDemoCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoCrudApplication.class, args);
    }

}
