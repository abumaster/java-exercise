package com.abumaster.example.springworkfront.controller;

import com.abumaster.example.springworkfront.service.SseDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/1
 */
@RestController
@RequestMapping("/api/v1/sse")
@Slf4j
@EnableAsync
public class SseDemoController {
    @Resource
    private SseDemoService sseDemoService;

    @GetMapping(value = "/events/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handlerSse(@PathVariable("id")String id) {
        SseEmitter sseEmitter = new SseEmitter();
        sseDemoService.putEmitter(id, sseEmitter);
        return sseEmitter;
    }

    @PostMapping("/send")
    public void sendMsg(String id, String msg) {
        sseDemoService.sendMsg(msg, id);
    }

}
