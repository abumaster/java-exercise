package com.abumaster.middleware.redis.queuedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * redis监听器
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/5/30
 */
@Component
@Slf4j
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.debug("===============================================");
        log.debug("从消息通道={}监听到消息",new String(bytes));
        log.debug("从消息通道={}监听到消息",new String(message.getChannel()));
        log.debug("元消息={}",new String(message.getBody()));
        // 新建一个用于反序列化的对象，注意这里的对象要和前面配置的一样
        // 因为我前面设置的默认序列化方式为GenericJackson2JsonRedisSerializer
        // 所以这里的实现方式为GenericJackson2JsonRedisSerializer
        RedisSerializer serializer=new GenericJackson2JsonRedisSerializer();
        log.info("线程id:{},反序列化后的消息={}",Thread.currentThread().getName(),serializer.deserialize(message.getBody()));
        log.debug("===============================================");
    }
}
