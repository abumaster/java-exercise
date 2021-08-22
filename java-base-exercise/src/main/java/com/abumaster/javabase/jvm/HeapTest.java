package com.abumaster.javabase.jvm;

import cn.hutool.system.SystemUtil;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/15
 */
public class HeapTest {
    public static void main(String[] args) {
        //返回 JVM 堆大小
        long initalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        //返回 JVM 堆的最大内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;

        System.out.println("-Xms : "+initalMemory + "M");
        System.out.println("-Xmx : "+maxMemory + "M");
        System.out.println(SystemUtil.getMaxMemory()/ 1024 /1024);
        System.out.println("系统内存大小：" + initalMemory * 64 / 1024 + "G");
        System.out.println("系统内存大小：" + maxMemory * 4 / 1024 + "G");

        HeapTest heapTest=new HeapTest();
        if(heapTest instanceof HeapTest) {
            System.out.println("true");
        }
    }
}
