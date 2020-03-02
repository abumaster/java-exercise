package com.abumaster.springmqdemo.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 生产者服务
 * @author zgh
 */
@Service
public class ProducerService {
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

    public void sendMessageToQueue(final String msg){
        jmsTemplate.send(queueDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * 发送消息到topic中
     * @param msg 消息
     */
    public void sendMessageToTopic(final String msg){
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
