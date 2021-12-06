package com.abumaster.multiuse;

import com.abumaster.HelloNative;

/**
 * 多线程调用本地方法
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/12/6
 */
public class ProcessThread implements Runnable{
    /** 构造函数初始化*/
    private final HelloNative helloNative;
    public ProcessThread(HelloNative helloNative) {
        this.helloNative = helloNative;
    }
    @Override
    public void run() {
        System.out.println(this.helloNative.echoStr(Thread.currentThread() + " print!"));

    }
}
