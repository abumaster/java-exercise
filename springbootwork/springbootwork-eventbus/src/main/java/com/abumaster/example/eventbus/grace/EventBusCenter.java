package com.abumaster.example.eventbus.grace;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事件总线组件
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Component
@Slf4j
public class EventBusCenter {

    @Autowired
    private ApplicationContext applicationContext;

    /** 同步*/
    private final EventBus syncEventBus = new EventBus();

    /** 异步*/
    private final AsyncEventBus asyncEventBus = new AsyncEventBus(new ThreadPoolExecutor(4,8,
            100, TimeUnit.SECONDS,new ArrayBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy()));

    /** 同步发布*/
    public void postSync(Object event) {
        syncEventBus.post(event);
    }
    /** 异步发布*/
    public void postAsync(Object event) {
        asyncEventBus.post(event);
    }


    /**
     * 容器初始化后，自动注册所有的监听器
     *
     * @see EventBusListener
     */
    @PostConstruct
    public void registerAllListener() {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(EventBusListener.class);
        beansWithAnnotation.forEach((k,v)->{
            EventBusListener anno = v.getClass().getAnnotation(EventBusListener.class);
            String dsc = anno.name();
            log.info("注入：{} 到{}事件总线中", StringUtils.isEmpty(dsc)?k:dsc, anno.async()?"异步":"同步");
            if (anno.async()) {
                asyncEventBus.register(v);
            } else {
                syncEventBus.register(v);
            }
        });
    }


}
