package com.abumaster.example.workcommon.service;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 单例中有一个有限长度的队列，队列存放字符串并符合先进先出的机制，队列长度可通过接口进行动态设置
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class SingleQueue {
    /** 实际的数据存储*/
    private Queue<String> data;
    /** 构造函数*/
    private SingleQueue(int len) {
        this.data = new ArrayBlockingQueue<>(len);
    }
    /** 单例获取方式*/
    public static  SingleQueue getInstance() {
        return innerClass.singleQueue;
    }
    /** 队尾插入一个元素*/
    public boolean offer(String element) {
       return this.data.offer(element);
    }
    /** 取出对头元素*/
    public String poll() {
        return this.data.poll();
    }
    /** 重新设置队列的大小 舍弃最后入队的元素*/
    public boolean resizeQueue(int newSize) {
        synchronized (SingleQueue.class) {
            Object[] oldData = this.data.toArray();
            int oldLen = oldData.length;
            // 新的队列长度
            this.data = new ArrayBlockingQueue<>(newSize);
            for (int i = 0; i < newSize; i++) {
                if (i>=oldLen) {
                    break;
                }
                this.data.offer((String) oldData[i]);
            }
        }
        return true;
    }
    /** 内联类，单例获取的一种方式*/
     private static class innerClass {
        private static final SingleQueue singleQueue = new SingleQueue(10);
     }


    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                SingleQueue.getInstance().offer(""+i);
            }
            SingleQueue.getInstance().resizeQueue(5);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(SingleQueue.getInstance().poll());
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
