package com.abumaster.example.springworkredis.service.disruptordemo.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * 定义事件工厂，返回一个数据
 * 获取一个实例，填充数据
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
public class LongEventFactory implements EventFactory<LongEvent>
{
    @Override
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}