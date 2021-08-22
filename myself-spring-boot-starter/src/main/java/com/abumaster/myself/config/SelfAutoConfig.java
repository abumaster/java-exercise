package com.abumaster.myself.config;


import com.abumaster.myself.bean.AuthorInfoBean;
import com.abumaster.myself.service.AuthorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
@Configuration
@EnableConfigurationProperties(AuthorInfoBean.class)
@ConditionalOnClass(AuthorService.class)
@ConditionalOnProperty(prefix = "myself.author", value = "enabled", matchIfMissing = true)
public class SelfAutoConfig {

    @Bean
    @ConditionalOnMissingBean(AuthorService.class)
    public AuthorService authorService (AuthorInfoBean authorInfoBean) {
        return new AuthorService(authorInfoBean);
    }
}
