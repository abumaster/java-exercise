package com.abumaster.springmqdemo.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 消费者服务
 * @author zgh
 */
@Service
public class ConsumerService {

    /**
     * Jms模板
     */
    @Resource
    JmsTemplate jmsTemplate;

    /**
     * 队列消息
     */
    @Resource(name = "queueDestination")
    Destination queueDestination;

    @Resource(name = "topicDestination")
    Destination topicDestination;



}
