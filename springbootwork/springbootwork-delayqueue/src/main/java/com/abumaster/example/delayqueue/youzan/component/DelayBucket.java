package com.abumaster.example.delayqueue.youzan.component;

import com.abumaster.example.delayqueue.youzan.bean.DelayJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 延迟队列桶
 * 根据job的topic，放到不同的有序队列中
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Component
@Slf4j
public class DelayBucket {

    @Resource
    private RedisTemplate<String,DelayJob> redisTemplate;

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private int bucketSize=4;
    /** 名称*/
    private List<String> bucketNameList=new ArrayList<>();
    public DelayBucket() {
        bucketNameList=new ArrayList<>(bucketSize);
        for(int i=0;i<bucketSize;++i) {
            bucketNameList.add("bucket-"+i);
        }
    }

    private String getBucketName() {
        return bucketNameList.get(COUNTER.intValue()%bucketSize);
    }

    /**
     * 根据业务获取队列
     * @param bucket 桶
     * @return 队列
     */
    private BoundZSetOperations<String,DelayJob> getBucket(String bucket) {
        return redisTemplate.boundZSetOps(bucket);
    }

    /**
     * 添加延时任务
     * @param delayJob 延时任务
     */
    public void addDelayJob(DelayJob delayJob) {
        getBucket(getBucketName()).add(delayJob, delayJob.getDelayTime());
    }

    /**
     * 获取最新的延迟任务
     * @param index 业务
     * @return delay job
     */
    public DelayJob getFirstDelayJob(int index) {
        Set<ZSetOperations.TypedTuple<DelayJob>> tuples = getBucket(bucketNameList.get(index))
                .rangeWithScores(0, 1);
        if (null == tuples || tuples.isEmpty()) {
            return null;
        }
        try {
            ZSetOperations.TypedTuple<DelayJob> typedTuple = (ZSetOperations.TypedTuple<DelayJob>) tuples.toArray()[0];
            return typedTuple.getValue();
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * 移除延迟任务
     * @param delayJob 延迟任务
     */
    public void removeDelay(DelayJob delayJob) {
        getBucket(getBucketName()).remove(delayJob);
    }


}
