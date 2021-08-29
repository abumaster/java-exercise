package com.abumaster.example.delayqueue.youzan.component;

import com.abumaster.example.delayqueue.youzan.bean.DelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 准备执行的job队列
 * 一个业务准备一个队列
 * 状态为准备好的job
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Component
@Slf4j
public class ReadyQueue {
    private static final String NAME="READY_QUEUE";

    @Resource
    private RedisTemplate<String, DelayJob> redisTemplate;
    /** 获取队列的名称*/
    private static String queryKey(String topic) {
        return NAME+"_"+topic;
    }
    /** 获取队列*/
    private BoundListOperations<String,DelayJob> getQueue(String topic) {
        return redisTemplate.boundListOps(queryKey(topic));
    }

    /**
     * 左边入队
     * @param delayJob 任务
     */
    public void push(DelayJob delayJob) {
        log.info("就绪的任务入队：{}",delayJob.getId());
        getQueue(delayJob.getTopic()).leftPush(delayJob);
    }

    /**
     * 右边出队列
     * @param topic 业务名称
     * @return delay job
     */
    public DelayJob pop(String topic) {
        DelayJob delayJob = getQueue(topic).rightPop();
        if (null == delayJob) {
            return null;
        }
        log.info("任务出队：{}", delayJob.getId());
        return delayJob;
    }
}
