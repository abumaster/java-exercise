package com.abumaster.javabase.jvm;


import cn.hutool.core.thread.ThreadUtil;

/**
 * test gc
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/17
 */
public class TestGC {
    private static final int MB=1*1024*1024;

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCTimeStamps  -XX:+UseSerialGC -XX:SurvivorRatio=6
     * -Xms=20MB -Xmx=20MB -Xmn=10MB  -XX:SurvivorRatio=8
     *
     */
    public static void main(String[] args) throws Exception{
        Thread.sleep(10000);
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
        allocation1=new byte[2*MB];
        Thread.sleep(10000);
        allocation2=new byte[2*MB];
        allocation3=new byte[2*MB];
        Thread.sleep(10000);
        // 触发minorGC 移到年轻代，和老年代
        allocation3=new byte[4*MB];
        Thread.sleep(10000);
        // 触发FullGC,内存不足分配失败
        allocation5=new byte[8*MB];

    }
}
