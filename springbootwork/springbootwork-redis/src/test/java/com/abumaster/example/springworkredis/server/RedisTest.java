package com.abumaster.example.springworkredis.server;

import com.abumaster.example.springworkredis.common.util.RedisUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * redis的测试
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Test
    public void testOne() {
        RedisUtils.set("one","one");
        Assert.assertEquals("one", RedisUtils.get("one"));
        RedisUtils.del("one");
        Assert.assertFalse(RedisUtils.exists("one"));
        CopyOnWriteArrayList list;

    }

    public void testTwo() {
    }
}
