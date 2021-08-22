package com.abumaster.example.springworkredis.service.disruptordemo.demo1;

import cn.hutool.core.thread.ThreadUtil;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 主函数
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/18
 */
public class MainRun {

    public static void main(String[] args) {
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        ThreadFactory threadFactory = ThreadUtil
                .createThreadFactoryBuilder()
                .setNamePrefix("disruptorThread-").build();
        // RingBuffer 大小，必须是 2 的 N 次方；
        int ringBufferSize = 16;
        // 创建一个对象
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory,
                ringBufferSize, threadFactory, ProducerType.SINGLE,
                new YieldingWaitStrategy());
        // 注册处理器
        disruptor.handleEventsWith(new LongEventHandler("worker1"),
                new LongEventHandler("worker2"));

        disruptor.start();
        // 生产者线程
        ExecutorService executorService = ThreadUtil.newExecutor(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new PublishEvent(disruptor,0));
        }

        // 关闭线程池和队列
        executorService.shutdown();
        disruptor.shutdown();

    }

    static class PublishEvent implements Runnable {

        private final Disruptor<LongEvent> disruptor;

        private int max;

        public PublishEvent(Disruptor<LongEvent> disruptor,int max) {
            this.disruptor = disruptor;
            this.max=max;
        }

        @Override
        public void run() {
            while (max++<10) {
                // 发布事件；
                RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
                // 获取需要
                long sequence = ringBuffer.next();

                try {
                    //获取该序号对应的事件对象；
                    LongEvent event = ringBuffer.get(sequence);
                    long data = Thread.currentThread().getId();
                    event.set(data);
                } finally {
                    //发布事件；
                    ringBuffer.publish(sequence);
                }
            }
        }
    }
}
