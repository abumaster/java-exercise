package com.abumaster.mqdemo.demo2;

import javax.jms.*;

/**
 * 消费监听器
 * @author zgh
 */
public class ConsumerListener implements MessageListener {

    public void onMessage(Message message) {
        // 如果是文件消息
        if(message instanceof TextMessage) {
            try {
                System.out.println("接收到文本消息为:"+((TextMessage)message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if(message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage)message;
            try {
                System.out.println("接收到对象消息为:"+objectMessage.getObject());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
