package com.abumaster.example.workcommon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        if (bean instanceof AggregateService || "aggregateService".equals(beanName)) {
            log.info("初始化之前:{}",beanName);
        }
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException 
    {
        if (bean instanceof AggregateService || "aggregateService".equals(beanName)) {
            log.info("初始化之后:{}",beanName);
        }
        return bean;
    }
}