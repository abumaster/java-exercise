package com.abumaster.middleware.redis.queuedemo;

import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * 单元测试
 * @author zgf
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PublishDemoTest {

    @Resource
    private PublishDemo publishDemo;

    @Test
    public void publishStrMessage() {
        ConcurrencyTester concurrencyTester = new ConcurrencyTester(100);
        concurrencyTester.test(()-> publishDemo.publish(RandomUtil.randomString(64)));
        ThreadUtil.sleep(10*1000);
    }

}