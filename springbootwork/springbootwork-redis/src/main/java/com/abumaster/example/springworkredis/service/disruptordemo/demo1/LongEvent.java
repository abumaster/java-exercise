package com.abumaster.example.springworkredis.service.disruptordemo.demo1;

/**
 * event
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
public class LongEvent
{
    private long value;

    public void set(long value)
    {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}