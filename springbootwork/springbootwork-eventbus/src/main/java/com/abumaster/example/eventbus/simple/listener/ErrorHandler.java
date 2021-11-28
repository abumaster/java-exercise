package com.abumaster.example.eventbus.simple.listener;

import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Slf4j
public class ErrorHandler implements SubscriberExceptionHandler {

    @Override
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
       log.info(" 事件：{}错误！{}",context.getEvent(), exception.toString());
    }
}
