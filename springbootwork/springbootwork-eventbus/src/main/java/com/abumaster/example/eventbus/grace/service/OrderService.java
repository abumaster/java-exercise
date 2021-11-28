package com.abumaster.example.eventbus.grace.service;

import com.abumaster.example.eventbus.grace.EventBusCenter;
import com.abumaster.example.eventbus.simple.event.OrderEvent;
import com.abumaster.example.eventbus.simple.publisher.OrderGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 向事件总线中发布消息
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Service
@Slf4j
public class OrderService {

    /** 注入事件中心服务*/
    @Resource
    private EventBusCenter eventBusCenter;


    public void generateOrder() {
        OrderEvent orderEvent = OrderGenService.generateOrder();
        log.info("生成一个订单：{}", orderEvent.getId());
        eventBusCenter.postAsync(orderEvent);
        eventBusCenter.postSync(orderEvent);
    }

}
