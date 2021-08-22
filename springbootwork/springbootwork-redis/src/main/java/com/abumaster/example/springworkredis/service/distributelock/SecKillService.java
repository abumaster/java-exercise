package com.abumaster.example.springworkredis.service.distributelock;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.abumaster.example.springworkredis.common.RedisConfig;
import com.abumaster.example.springworkredis.common.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁的实现
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/15
 */
@Service
@Slf4j
public class SecKillService {

    public Integer num = 200;
    private static final String KEY="LOCK_PRODUCT";

    private RLock rLock = RedisConfig.redissonClient().getLock("redisson_LOCK");
    /** 不加锁的版本*/
    public void killProductNoLock() {
        log.info("{} 商品:{}",Thread.currentThread().getName(), num--);
    }
    /** 加锁*/
    public void killProductRedisLock() {
        Long setnx = RedisUtils.setnx(KEY, IdUtil.randomUUID(), 10*1000);
        // 获取到锁
        if (setnx == 1) {
            // 如果业务很耗时，防止别人获取不到锁，设置一个过期时间
            RedisUtils.setex(KEY, 10);
            log.info("{} 商品:{}", Thread.currentThread().getName(), num--);
            RedisUtils.del(KEY);
        } else {
            log.warn("没获取到锁");
        }

    }

    public void killProductRedisson() {
        try {
            if (rLock.tryLock(10, TimeUnit.SECONDS)) {
                log.info("Redisson {} 商品:{}", Thread.currentThread().getName(), num--);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            rLock.unlock();
        }
    }
}
