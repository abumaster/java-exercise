package com.abumaster.middleware.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.func.Func;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * jdk中的一些方法的使用
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/1/15
 */
public class MethodUsing {

    private static void testOptional(String val) {
        System.out.println(Optional.ofNullable(val).orElse("default"));
        Optional.ofNullable(val).ifPresent(System.out::println);
        System.out.println(Optional.ofNullable(val).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("predicate");
            }
        }).orElse("no"));
    }

    private static Function<String,Long> dateTransfer = (x)->{
        return DateUtil.parse(x,"yyyy-MM-dd HH:mm:ss").getTime()/1000;
    };

    public static void main(String[] args) {
        testOptional("predite12");

        System.out.println(dateTransfer.apply("2020-10-09 10:09:10"));
    }
}
