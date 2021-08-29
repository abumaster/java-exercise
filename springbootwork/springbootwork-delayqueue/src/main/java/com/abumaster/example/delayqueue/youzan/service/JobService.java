package com.abumaster.example.delayqueue.youzan.service;

import cn.hutool.core.date.DateUtil;
import com.abumaster.example.delayqueue.youzan.bean.DelayJob;
import com.abumaster.example.delayqueue.youzan.bean.Job;
import com.abumaster.example.delayqueue.youzan.component.DelayBucket;
import com.abumaster.example.delayqueue.youzan.component.JobPool;
import com.abumaster.example.delayqueue.youzan.component.ReadyQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 延迟任务服务
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Service
@Slf4j
public class JobService {
    @Resource
    private JobPool jobPool;

    @Resource
    private DelayBucket delayBucket;

    @Resource
    private ReadyQueue readyQueue;

    /**
     * 添加任务
     * @param job 任务
     * @return 延时队列
     */
    public DelayJob addJob(Job job) {
        job.setStatus(Job.JobStatus.DELAY.getS());
        // 添加元数据
        jobPool.addJob(job);
        // 添加延时队列
        DelayJob delayJob = new DelayJob(job);
        delayBucket.addDelayJob(delayJob);
        return delayJob;
    }

    /**
     * 获取待执行的job
     * @param topic 业务
     * @return job
     */
    public Job getReadyJob(String topic) {
        DelayJob pop = readyQueue.pop(topic);
        if (pop == null || pop.getId() == null){
            // 没有就绪的任务
            return new Job();
        }
        // 获取元数据
        Job job = jobPool.getJob(pop.getId());

        if (job == null) {
            job = getReadyJob(topic);
        } else {
            job.setStatus(Job.JobStatus.RESERVED.getS());
            pop.setDelayTime(DateUtil.currentSeconds()+job.getTtr());
            jobPool.addJob(job);
            delayBucket.addDelayJob(pop);
        }
        return job;
    }
    /** 完成任务，删除*/
    public void finishJob(Long jobId) {
        jobPool.deleteJob(jobId);
    }

}
