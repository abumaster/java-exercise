package com.abumaster.mqdemo.demo3;

import org.apache.activemq.ActiveMQConnectionFactory;

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
        // 创建session会话,由客户端进行消息的确认
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //创建一个消息队列
        Queue txQueue = session.createQueue("my-queue");
        // 创建消息的生产者
        MessageProducer producer = session.createProducer(txQueue);
//        //创建一个topic
//        Topic topic = session.createTopic("my-topic");
//        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i < 10; i++) {
            // 创建一个消息
            TextMessage textMessage = session.createTextMessage("hello mq 第"+i+"个测试");
            // 发送消息
            producer.send(textMessage);
            System.out.println("发送第"+i+"个消息");
        }
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 关闭
        producer.close();
        session.close();
        connection.close();

    }
}
