package com.abumaster.example.delayqueue.simple;

import cn.hutool.core.thread.ThreadUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/26
 */
public class UseMain {
    public static void main(String[] args) {
        Order Order1 = new Order("Order1", 5L, TimeUnit.SECONDS);
        Order Order2 = new Order("Order2", 10L, TimeUnit.SECONDS);
        Order Order3 = new Order("Order3", 15L, TimeUnit.SECONDS);
        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.put(Order1);
        delayQueue.put(Order2);
        delayQueue.put(Order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            Order task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            ThreadUtil.sleep(1000);
        }
    }
}
