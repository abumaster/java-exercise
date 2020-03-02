package com.abumaster.mqdemo.demo3;

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
        // 开启连接
        connection.start();
        // 创建session会话 第一个参数是否开启事务，第二个表示消息确认方式，开启事务时不需要手动签收
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //创建一个消息队列
        Queue txQueue = session.createQueue("my-queue");
        //接收消息
        MessageConsumer consumer = session.createConsumer(txQueue);
        //如果接收到消息客户端不进行确认，那么消息依然存在于消息队列中
        Message message = consumer.receive();
        
        message.acknowledge();
        System.out.println("接收到的消息为:"+message.toString());
        System.out.println("文本内容为:"+((TextMessage)message).getText());

        // 关闭
        session.close();
        connection.close();
    }
}
