package com.abumaster.middleware.rmdb.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis plus的配置文件
 * 配置扫描路径
 * 强制使用cglib代理
 * 分页插件
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2020/10/20
 */
@Configuration
@MapperScan("com.abumaster.middleware.rmdb.mapper")
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPlusConfig {

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }
}
