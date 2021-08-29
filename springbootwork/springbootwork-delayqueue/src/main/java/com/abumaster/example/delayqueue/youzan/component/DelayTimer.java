package com.abumaster.example.delayqueue.youzan.component;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.abumaster.example.delayqueue.youzan.bean.DelayJob;
import com.abumaster.example.delayqueue.youzan.bean.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时器：
 *  负责实时扫描各个Bucket，并将delay时间大于等于当前时间的Job放入到对应的Ready Queue
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Component
@Slf4j
public class DelayTimer {

    @Resource
    private JobPool jobPool;

    @Resource
    private DelayBucket delayBucket;

    @Resource
    private ReadyQueue readyQueue;


    private int bucketSize=4;

    @PostConstruct
    public void init() {
        ExecutorService executorService = ThreadUtil.newExecutor(bucketSize, bucketSize, 100);
        AtomicInteger index = new AtomicInteger(0);
        for (int i=0;i<bucketSize;++i) {
            executorService.execute(()->processDelayJob(index.getAndIncrement()));
        }
    }

    public void processDelayJob(int index)  {
        // 处理每一个业务
        while (true) {
            DelayJob delayJob = delayBucket.getFirstDelayJob(index);
            if (null == delayJob) {
                ThreadUtil.sleep(1000);
                continue;
            }
            // 是否到期
            if (delayJob.getDelayTime() > DateUtil.currentSeconds()) {
                continue;
            }
            // 是否已经不存在
            Job job = jobPool.getJob(delayJob.getId());
            if (null == job) {
                log.info("任务不存在,移除");
                delayBucket.removeDelay(delayJob);
                continue;
            }

            if (job.getStatus()== Job.JobStatus.RESERVED.getS()) {
                log.info("处理超时的任务");
                processTtrJob(delayJob, job);
            } else {
                log.info("处理延时的任务");
                processDelayJob(delayJob, job);
            }

        }
    }

    /**
     * 处理超时的任务：已经被消费了，但是没有设置任务的状态
     * 更新延迟时间后，重新放入队列
     * @param delayJob delay job
     * @param job job
     */
    private void processTtrJob(DelayJob delayJob, Job job) {
        // 设置状态
        job.setStatus(Job.JobStatus.DELAY.getS());
        jobPool.addJob(job);
        delayBucket.removeDelay(delayJob);
        long newDelay = DateUtil.currentSeconds()+job.getDelay();
        delayJob.setDelayTime(newDelay);
        delayBucket.addDelayJob(delayJob);
    }

    /**
     * 处理超时的任务：
     *  放入就绪队列
     * @param delayJob delay
     * @param job job
     */
    private void processDelayJob(DelayJob delayJob , Job job) {
        // 修改job的状态
        job.setStatus(Job.JobStatus.READY.getS());
        jobPool.addJob(job);
        // 添加到就绪队列
        readyQueue.push(delayJob);
        delayBucket.removeDelay(delayJob);
    }

}
