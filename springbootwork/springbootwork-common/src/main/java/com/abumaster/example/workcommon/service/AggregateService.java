package com.abumaster.example.workcommon.service;

import com.abumaster.example.workcommon.service.impl.ServiceOne;
import com.abumaster.example.workcommon.service.impl.ServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/31
 */
@Service(value = "aggregateService")
@Slf4j
@Async
public class AggregateService implements BeanNameAware,
        BeanFactoryAware,
        ApplicationContextAware,
        InitializingBean,
        DisposableBean {



    public void queryOne() {

    }

    public AggregateService() {
        log.info("构造函数。。。。");
    }


    @Override
    public void setBeanName(String s) {
        log.info("设置Bean的名称，进行处理:{}",s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
      log.info("Bean工厂");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("应用上下文");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("全部属性设置成功");
    }

    @Override
    public void destroy() throws Exception {
        log.info("销毁destory");
    }
}
