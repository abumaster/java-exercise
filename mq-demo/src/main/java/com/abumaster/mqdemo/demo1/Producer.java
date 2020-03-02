package com.abumaster.mqdemo.demo1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.omg.CORBA.UserException;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.*;

/**
 * 生产者
 * @author zgh
 */
public class Producer {

    public static void main(String[] args) throws Exception{
        String brokerUrl="tcp://111.229.201.221:61616";
        //创建mq的连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        //创建一个连接
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 创建session会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建一个消息队列
        Queue txQueue = session.createQueue("my-queue");
        // 创建消息的生产者
        MessageProducer producer = session.createProducer(txQueue);
//        //创建一个topic
//        Topic topic = session.createTopic("my-topic");
//        MessageProducer producer = session.createProducer(topic);
        // 创建一个消息
        TextMessage textMessage = session.createTextMessage("hello mq 第一个测试");
        // 发送消息
        producer.send(textMessage);

        // 关闭
        producer.close();
        session.close();
        connection.close();

    }
}
