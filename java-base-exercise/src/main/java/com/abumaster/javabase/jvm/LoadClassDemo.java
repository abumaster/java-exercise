package com.abumaster.javabase.jvm;

import java.sql.DriverManager;

/**
 * 如何破坏双亲委派
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/14
 */
public class LoadClassDemo {

    public static void main(String[] args) {
        int i=10;
        int j=23;
        int sum = i+j;

        method1();
        System.out.println(sum);
    }


    private static void method1() {
        System.out.println("invoke method1");
        int x,y;
        x=10;
        y=9082;

        method2();
    }

    private static void method2() {
        System.out.println("invoke method2");
    }

    public void method3(String args) {
        int i=10;
        {
            int j=2;
            j=i+j;
        }
        int c=i+22;
    }
}
