package com.abumaster.example.workconfig.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * apollo的配置文件
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
@Configuration
@EnableApolloConfig(value = {"application","area-info.json","common-cfg"})
public class MyApolloConf {
}
