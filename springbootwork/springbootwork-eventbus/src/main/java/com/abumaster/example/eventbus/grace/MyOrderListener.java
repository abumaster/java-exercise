package com.abumaster.example.eventbus.grace;

import com.abumaster.example.eventbus.simple.event.OrderEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 订单监听器
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Component
@EventBusListener(name = "订单监听", async = false)
@Slf4j
public class MyOrderListener {
    @Subscribe
    private void processOne(OrderEvent orderEvent) {
        log.info("为订单：{}，发送消息！", orderEvent.getId());
    }
    @Subscribe
    private void processTwo(OrderEvent orderEvent) {
        log.info("我处理了订单【{}】", orderEvent.getId());
    }
}
