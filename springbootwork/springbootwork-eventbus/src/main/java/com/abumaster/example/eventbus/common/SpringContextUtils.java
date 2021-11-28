package com.abumaster.example.eventbus.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * 获取bean的方法
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Component
public class SpringContextUtils implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringContextUtils.beanFactory = configurableListableBeanFactory;
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return beanFactory.getBean(clz);
    }

    public static <T> List<T> getBeansOfType(Class<T> type) {
        return beanFactory.getBeansOfType(type).entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }

    /**
     * 获取有注解的所有bean
     *
     * @param annotationType 注解类型
     * @return List
     */
    public static List<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(annotationType);
        // java 8 的写法，将 map 的 value 收集起来到一个 list 中
        return beansWithAnnotation.entrySet().stream().map(entry->entry.getValue()).collect(Collectors.toList());
    }
}