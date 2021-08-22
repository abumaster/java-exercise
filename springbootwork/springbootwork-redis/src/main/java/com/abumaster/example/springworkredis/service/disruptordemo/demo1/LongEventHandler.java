package com.abumaster.example.springworkredis.service.disruptordemo.demo1;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.EventHandler;

/**
 * 事件的处理器
 * 定义事件的具体实现
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    private String name;

    public LongEventHandler(){}
    public LongEventHandler(String name) {
        this.name=name;
    }
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        ThreadUtil.sleep(RandomUtil.randomLong(500));
        System.out.println("处理器名称："+name+" 线程："+Thread.currentThread().getName()+" 处理事件: " + event);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}