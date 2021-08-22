package com.abumaster.javabase.async;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步编程的新方法：CompletableFuture
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/9
 */
public class TestCompletableFuture {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future =  new CompletableFuture<>();
        return future;
    }

    public static void main(String[] args) {
        final CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {
                    Console.log("{}等待获取结果...", this.getName());
                    System.out.println(this.getName() + ": " + f.get());
                    Console.log("{}获取结果完成!",this.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        ThreadUtil.sleep(2000);
        f.complete(100);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
