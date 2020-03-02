package com.abumaster.springmqdemo.controller;

import com.abumaster.springmqdemo.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 控制器
 * @author zgh
 */
@Controller
@Slf4j
public class HelloController {

    @Resource
    ProducerService producerService;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        for (int i = 0; i < 10; i++) {
            log.info("发送消息 --{}",i);
            producerService.sendMessageToQueue("队列消息:"+i);
        }
        return "hello world";
    }
}
