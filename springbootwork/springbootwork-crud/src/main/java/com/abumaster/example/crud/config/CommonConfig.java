package com.abumaster.example.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * 开启事务的管理
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/6
 */
@Configuration
@EnableTransactionManagement
public class CommonConfig {
}
