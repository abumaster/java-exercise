package com.abumaster.springmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听器
 * @author zgh
 */
@Component
@Slf4j
public class QueueMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            log.info("======== 开始接收文件消息 ========");
            TextMessage textMessage = (TextMessage)message;
            try{
                log.info("消息内容:{}",textMessage.getText());
            }catch (JMSException e){
                log.error("接收消息异常:{}",e.toString());
            }
            log.info("======== 接收文件消息成功 ========");
        }
    }

}
