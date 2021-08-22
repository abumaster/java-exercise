package com.abumaster.example.workconfig.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/20
 */
@Slf4j
@Component
@EnableScheduling
public class PrinterLogTask {

    @Scheduled(cron = "0/30 * * * * ?")
    public void testOne() {
        String name = Thread.currentThread().getName();
        log.debug("debug级别日志:{}",name);
        log.info("info级别日志:{}",name);
        log.warn("warn级别日志:{}",name);
        log.error("error级别日志:{}",name);
    }
}
