package com.abumaster;

/**
 * 本地方法
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/12/6
 */
public class HelloNative {


    /** 回显字符串*/
    public native String echoStr(String msg);
    /** 两数相加*/
    public native int add(int a, int b);

}
