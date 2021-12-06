package com.abumaster;


/**
 * 使用
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/12/6
 */
public class UsageNativeDll {
    // 加载动态库
    static {
        System.loadLibrary("HelloNative");
        System.out.println("dll 加载成功！");
    }

    public static void main(String[] args) {
        // 实例对象
        HelloNative helloNative = new HelloNative();
        // 调用方法
        System.out.println(helloNative.add(3,3));
        System.out.println(helloNative.echoStr("hello native method"));

    }
}
