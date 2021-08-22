package com.abumaster.javabase.jvm;

import java.util.WeakHashMap;

/**
 * 弱引用的栗子
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/21
 */
public class WeakReferenceDemo {

    /**
     * WeakHashMap key为弱引用对象，不再需要时则回收
     */
    private static void weakMap(){
        WeakHashMap<String,String> map = new WeakHashMap<>();
        String key = new String("key1");
        String value="val1";
        map.put(key,value);
        key=null;
        System.out.println("回收前的弱引用："+map.get("key1")); // val1
        System.gc();
        System.out.println("回收后的弱引用："+map.get("key1")); // null
    }

    public static void main(String[] args) {
        weakMap();
    }
}
