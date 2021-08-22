package com.abumaster.example.springworkredis.server;

import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import com.abumaster.example.springworkredis.service.distributelock.SecKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LockServiceTest {

    @Resource
    private SecKillService secKillService;

    @Test
    public void testOne() {
        // 50个线程并发读
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(50, () -> {
            secKillService.killProductRedisLock();
        });
        System.out.println(tester.getInterval());
    }

    @Test
    public void testTwo() {
        // 50个线程并发读
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(50, () -> {
            secKillService.killProductRedisson();
        });
        System.out.println(tester.getInterval());
    }
}
