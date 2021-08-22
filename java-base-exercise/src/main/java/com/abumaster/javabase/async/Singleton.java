package com.abumaster.javabase.async;

/**
 * 单例模式
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/9
 */
public class Singleton {
    private static volatile Singleton singleton;
    private Singleton(){}

    public static Singleton getInstance() {
        if (singleton==null) {
            synchronized (Singleton.class) {
                if (singleton==null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
