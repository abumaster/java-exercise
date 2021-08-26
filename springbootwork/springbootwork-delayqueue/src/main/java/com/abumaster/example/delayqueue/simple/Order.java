package com.abumaster.example.delayqueue.simple;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 实现delay接口的对象
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/26
 */
@Data
public class Order implements Delayed {

    private String id;
    private String name;
    /** 创建时间*/
    private Long createTime;
    /** 持续时间*/
    private Long time;
    /** 结束时间*/
    private Long endTime;

    public Order(){}
    public Order(String name, Long time, TimeUnit timeUnit) {
        this.id= IdUtil.fastUUID();
        this.name = name;
        this.createTime = System.currentTimeMillis();
        this.time = this.createTime+timeUnit.toMillis(time);
        this.endTime = -1L;
    }
    /** 获取剩余的时间*/
    @Override
    public long getDelay(TimeUnit unit) {
        return this.time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order order = (Order)o;
        long diff = this.time-order.time;
        if (diff<=0) {
            return -1;
        } else {
            return 1;
        }
    }
}
