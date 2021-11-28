package com.abumaster.example.eventbus.simple.listener;

import com.abumaster.example.eventbus.simple.event.OrderEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单的事件监听器.
 *  使用注解，声明 订阅了什么事件 @Subscribe
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Slf4j
public class SimpleEventListener {

    @Subscribe
    public void customerListener(OrderEvent orderEvent) {
        log.info("[消费者] 订单：{}, 商家处理中！", orderEvent.getId());
    }
    @Subscribe
    public void merchantListener(OrderEvent orderEvent) {
        log.info("[商家] 处理订单：{}",  orderEvent.getId());
        int a=1/0;
    }

}
