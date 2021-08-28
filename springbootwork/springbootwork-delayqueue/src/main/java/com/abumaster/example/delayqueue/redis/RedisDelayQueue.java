package com.abumaster.example.delayqueue.redis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/26
 */
public class RedisDelayQueue {
    private static final String DELAY_QUEUE="DELAY_QUEUE";
    public static void main(String[] args) {
        // 添加几个元素
        for (int i = 0; i < 10; i++) {

            RedisUtils.zadd(DELAY_QUEUE, System.currentTimeMillis()/1000+RandomUtil.randomInt(20,30), "order"+i);
        }

        // 启动一个线程去循环消费
        while (true) {
            // 获取排序的数据
            Set<Tuple> tuples = RedisUtils.zrangeByScoreWithScores(DELAY_QUEUE, 0, System.currentTimeMillis()/1000);
            if (tuples.isEmpty()) {
                ThreadUtil.sleep(100);
                continue;
            }
            // 获取第一个元素
            String element = ((Tuple) tuples.toArray()[0]).getElement();
            long score = (long)((Tuple) tuples.toArray()[0]).getScore();
            // 与当前时间比较
            long now = System.currentTimeMillis();
            if (now >=score) {
                RedisUtils.zrem(DELAY_QUEUE, element);
                System.out.println(DateUtil.now()+" 到期："+element);
            }
            // 如果为空，退出
            if (RedisUtils.zcard(DELAY_QUEUE)<=0) {
                System.out.println(DateUtil.now()+" 队列为空："+element);
                return;
            }
            ThreadUtil.sleep(500);

        }

    }
}
