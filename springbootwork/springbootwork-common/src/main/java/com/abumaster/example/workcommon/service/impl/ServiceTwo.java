package com.abumaster.example.workcommon.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 服务2
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/31
 */
@Service
@Slf4j
public class ServiceTwo {

    public void display() {
        log.info("服务2的调用...");
    }
}
