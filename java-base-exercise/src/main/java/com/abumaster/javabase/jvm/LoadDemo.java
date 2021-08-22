package com.abumaster.javabase.jvm;

/**
 * 类加载子系统的测试类
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/13
 */
public class LoadDemo {

    public LoadDemo() {
        System.out.println("=== 构造函数 ===");
    }

    /**
     * 一个函数
     * @param msg msg
     */
    public void print(String msg) {
        System.out.println(String.format("调用成功，传入的参数：%s",msg));
    }
}
