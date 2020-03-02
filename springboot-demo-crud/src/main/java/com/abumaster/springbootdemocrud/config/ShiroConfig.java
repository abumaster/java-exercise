package com.abumaster.springbootdemocrud.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro权限管理的配置
 * @author zgh
 */
@Configuration
public class ShiroConfig {
    @Bean
    ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * 设置认证管理器
     * @return
     */
    @Bean
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(shiroRealm());
        return webSecurityManager;
    }
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //设置不拦截的url地址
        chainDefinition.addPathDefinition("/api/manager/user/login","anon");
        //swagger-ui不认证
        chainDefinition.addPathDefinition("/swagger-ui.html","anon");
        chainDefinition.addPathDefinition("/swagger-resources","anon");
        chainDefinition.addPathDefinition("/v2/api-docs","anon");
        chainDefinition.addPathDefinition("/webjars/springfox-swagger-ui/*","anon");
        chainDefinition.addPathDefinition("/**","authc");
        return chainDefinition;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        //defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
