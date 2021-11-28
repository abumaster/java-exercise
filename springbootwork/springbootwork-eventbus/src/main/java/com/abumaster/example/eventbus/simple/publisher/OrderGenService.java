package com.abumaster.example.eventbus.simple.publisher;

import cn.hutool.core.util.RandomUtil;
import com.abumaster.example.eventbus.simple.event.OrderEvent;
import com.abumaster.example.eventbus.simple.listener.ErrorHandler;
import com.abumaster.example.eventbus.simple.listener.SimpleEventListener;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Executors;

/**
 * 事件发布
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Slf4j
public class OrderGenService {
    /** 声明一个事件总线*/
    private static final EventBus EVENT_BUS = new EventBus(new ErrorHandler());
    /** 异步的消息总线*/
    private static final AsyncEventBus ASYNC_EVENT_BUS = new AsyncEventBus(Executors.newCachedThreadPool(),new ErrorHandler());

    // 注册监听器
    static {
        EVENT_BUS.register(new SimpleEventListener());
        ASYNC_EVENT_BUS.register(new SimpleEventListener());
    }

    /** 发布一个事件*/
    public static void post(OrderEvent orderEvent) {
        EVENT_BUS.post(orderEvent);
    }
    public static void asyncPost(OrderEvent orderEvent) {
        ASYNC_EVENT_BUS.post(orderEvent);
    }
    /** 同步总线的使用*/
    private static void syncDemo() {
        OrderEvent order = generateOrder();
        log.info("订单生成：{}, {}", order.getId(), order.toString());
        OrderGenService.post(order);
    }
    /** 模拟生成一个订单*/
    public   static  OrderEvent generateOrder() {
        OrderEvent order = new OrderEvent();
        order.setId(RandomUtil.randomLong(1000,100000));
        order.setUserId("jd_user");
        order.setDesc("这是一个订单");
        order.setDate(new Date());
        return order;
    }
    /** 异步调用*/
    private static void asyncDemo() {
        for (int i = 0; i < 5; i++) {
            OrderEvent orderEvent =  generateOrder();
            log.info("订单生成：{}, {}", orderEvent.getId(), orderEvent.toString());
            OrderGenService.asyncPost(orderEvent);
        }
    }
    public static void main(String[] args) {
//        syncDemo();
        asyncDemo();
    }
}
