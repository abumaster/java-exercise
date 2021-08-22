package com.abumaster.example.workcommon.task;


import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/4
 */
@Async
@Service
@Slf4j
public class AsyncTask {


    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void taskOne() {
        log.info("线程id：[{}]的定时任务执行...", Thread.currentThread().getName());
    }
}
