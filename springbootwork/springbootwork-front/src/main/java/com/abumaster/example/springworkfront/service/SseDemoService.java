package com.abumaster.example.springworkfront.service;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sse服务端主动推送数据
 * service send event
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/1
 */
@Service
@Slf4j
@EnableScheduling
public class SseDemoService {

    private static final Map<String, SseEmitter> EMITTER_MAP = new ConcurrentHashMap<>();

    public void putEmitter(String id, SseEmitter e) {
        EMITTER_MAP.put(id, e);
    }
    public void removeEmitter(String id) {
        EMITTER_MAP.remove(id);
    }

    public void sendMsg(String msg,String id) {
        try {
            EMITTER_MAP.get(id).send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
