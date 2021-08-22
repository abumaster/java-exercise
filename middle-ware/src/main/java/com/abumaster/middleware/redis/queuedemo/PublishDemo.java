package com.abumaster.middleware.redis.queuedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息发送者
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/5/30
 */
@Service
public class PublishDemo {
    @Autowired
    private RedisTemplate redisTemplate;

    /** 发送消息*/
    public void publish(Object msg){
        redisTemplate.convertAndSend("demo-channel",msg);
    }
}
