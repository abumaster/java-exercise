package com.abumaster.mqdemo.demo2;

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connectionFactory.setTrustAllPackages(true);
        //创建一个连接
        Connection connection = connectionFactory.createConnection();
        // 开启连接
        connection.start();
        // 创建session会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建一个消息队列
        Queue txQueue = session.createQueue("my-queue");
        //接收消息
        MessageConsumer consumer = session.createConsumer(txQueue);
        ObjectMessage objectMessage = (ObjectMessage)consumer.receive();
        //转换成封装的消息对象
        PersonBean personBean = (PersonBean)objectMessage.getObject();
        System.out.println("接收到的消息为:"+personBean.toString());

        // 关闭
        session.close();
        connection.close();
    }
}
