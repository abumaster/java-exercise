package com.abumaster.example.delayqueue.youzan.bean;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 放到延时队列中的信息
 * 存id和延迟时间的
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Data
public class DelayJob implements Serializable {
    private Long id;
    /** 秒级延迟*/
    private long delayTime;
    private String topic;
    public DelayJob(Job job) {
        this.id=job.getId();
        this.delayTime = DateUtil.currentSeconds() +job.getDelay();
        this.topic = job.getTopic();
    }
    public DelayJob(Object id, Double s) {
        this.id = (Long)id;
        this.delayTime = DateUtil.currentSeconds()+s.longValue();
    }
}
