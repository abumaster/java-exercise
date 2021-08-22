package com.abumaster.javabase.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * oom的异常测试
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/20
 */
public class TestOom {
    /**
     * GC Overhead Limit Exceeded.
     */
    public static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();
        while (true) {
            dataMap.put(r.nextInt(), String.valueOf(r.nextInt()));
        }
    }

    private static int feb(int i) {
        return i*feb(i-1);
    }
    public static void stackOverFlow(){
        feb(1);
    }

    public static void main(String[] args) {
        addRandomDataToMap();
//        stackOverFlow();
    }

}
