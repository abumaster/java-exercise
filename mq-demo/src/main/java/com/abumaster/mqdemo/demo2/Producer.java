package com.abumaster.mqdemo.demo2;

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
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        //信任
        connectionFactory.setTrustAllPackages(true);
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

        // 创建一个消息
        ObjectMessage objectMessage = session.createObjectMessage();
        PersonBean personBean = new PersonBean();
        personBean.setUid("123");
        personBean.setName("zgf");
        personBean.setAge(20);
        personBean.setMessage("hello object");
        objectMessage.setObject(personBean);
        // 发送消息
        producer.send(objectMessage);

        // 关闭
        producer.close();
        session.close();
        connection.close();

    }
}
