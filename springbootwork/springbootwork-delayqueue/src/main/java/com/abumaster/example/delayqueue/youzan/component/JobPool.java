package com.abumaster.example.delayqueue.youzan.component;

import com.abumaster.example.delayqueue.youzan.bean.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 存放job的元数据：
 *  提供 增、删、查操作
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Component
@Slf4j
public class JobPool {

    @Resource
    private RedisTemplate<String,Job> redisTemplate;

    /** 获取hash*/
    private BoundHashOperations<String, Long, Job> getPool() {
        return redisTemplate.boundHashOps("JOB_POOL");
    }
    public void addJob(Job job) {
        log.info("添加job：{}", job);
        getPool().put(job.getId(), job);
    }

    public void deleteJob(Long id) {
        log.info("删除job：{}", id);
        getPool().delete(id);
    }

    public Job getJob(Long id) {
        Job job = getPool().get(id);
        if (null == job) {
            log.error("没有该{}任务",id);
        }
        return job;
    }
}
