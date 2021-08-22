package com.abumaster.javabase.async;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import sun.misc.Unsafe;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Future的另一个优点是它比 更底层的Thread更易用。
 * 要使用Future，通常你只需要将耗时的操作封装在一个Callable对象中，再将它提交给ExecutorService，就可以了。
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/8
 */
public class SimpleFuture {

    /**
     * 比较耗时的一些二操作
     */
    private void doSomething() {
        int x=1;
        for (int i = 10; i < 100; i++) {
            x=x*i;
            ThreadUtil.sleep(RandomUtil.randomInt(10,20));
        }
        Console.log("result:{}",x);
    }
    private void doSome() {
        Console.log("做其他的一些事情！");
    }
    private void testSimpleFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Void> future = executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                doSomething();
                return null;
            }
        });
    doSome();
        try {
            future.get(10,TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException  e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Console.log("=== 运行结束 ===");
    }

    public static void main(String[] args) {
        //new SimpleFuture().testSimpleFuture();
        int i=10;
        AtomicInteger atomicInteger = new AtomicInteger(i);
        Console.log("previous={}",atomicInteger.getAndAdd(10));
        Console.log("result={}",atomicInteger.get());
    }
}
