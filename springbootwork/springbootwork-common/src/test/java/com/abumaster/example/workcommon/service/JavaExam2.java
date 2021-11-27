package com.abumaster.example.workcommon.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 匹配字符串
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class JavaExam2 {


    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("上头");
        String s="昨晚去酒吧嗨了，朋友搞了点粉，玩的爽的一比，今天睡到下午还有点上头，感觉身体被掏空。以后不敢这么玩了！！！再来几次人都要废了";
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()) {
            System.out.println("" + matcher.start() + ", " + matcher.end());
        } else { System.out.println("没有匹配到");
        }

    }
}
