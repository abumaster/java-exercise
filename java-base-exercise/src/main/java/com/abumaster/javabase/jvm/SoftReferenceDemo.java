package com.abumaster.javabase.jvm;

import java.lang.ref.SoftReference;

/**
 * 软引用的测试
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/21
 */
public class SoftReferenceDemo {

    /**
     * 强引用栗子
     */
    public static void strongReference() {
        Object obj1 = new Object();
        // 强引用
        Object obj2 = obj1;
        obj1=null;
        System.gc();
        System.out.println("对象1："+obj1);
        System.out.println("对象2："+obj2);
    }

    /**
     * 软引用，有充足的空间，则不会回收
     */
    public static void softReferenceHaveMem() {
        Object obj1 = new Object();
        // 软引用
        SoftReference<Object> ref1=new SoftReference<>(obj1);
        // 置空ref1也不会回收
        obj1=null;
        System.out.println("对象1："+obj1);
        System.out.println("软引用对象："+ref1.get());
    }

    /**
     * 不充足的内存空间，则会回收
     * -Xmx5m，先申请一个2M的被软引用，在申请一个3M的
     */
    public static void softReferenceNotEnoughMem(){
        byte[] bytes = new byte[2*1024*1024];
        // 软引用
        SoftReference<Object> ref1=new SoftReference<>(bytes);
        // 置空ref1也不会回收
        bytes=null;
        System.out.println("对象1："+bytes);

        System.out.println("==== 空间很足 ====\n软引用对象："+ref1.get());
        byte[] bytes3m=new byte[3*1024*1024];
        System.out.println("==== 空间不足 ====\n软引用对象："+ref1.get());
    }
    public static void main(String[] args) {
        softReferenceNotEnoughMem();
    }
}
