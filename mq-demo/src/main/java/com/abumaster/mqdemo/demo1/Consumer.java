package com.abumaster.mqdemo.demo1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费者
 * @author zgh
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        String brokerUrl="tcp://111.229.201.221:61616";
        //创建mq的连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        //创建一个连接
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("client-01");
        // 开启连接
        connection.start();
        // 创建session会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //创建一个消息队列
//        Queue txQueue = session.createQueue("my-queue");
//        //接收消息
//        MessageConsumer consumer = session.createConsumer(txQueue);
        Topic topic = session.createTopic("my-topic");
        MessageConsumer consumer = session.createDurableSubscriber(topic,"tx-topic-01");
        Message message = consumer.receive();
        System.out.println("接收到的消息为:"+message.toString());
        System.out.println("文本内容为:"+((TextMessage)message).getText());

        // 关闭
        session.close();
        connection.close();
    }
}
