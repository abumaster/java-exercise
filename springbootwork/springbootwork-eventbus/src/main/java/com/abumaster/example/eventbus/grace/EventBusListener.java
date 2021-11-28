package com.abumaster.example.eventbus.grace;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标识一个类是事件监听器
 *  有此标记的都会被注册到数据总线中
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBusListener {
    String name() default "";
    /** true为异步 ； false为同步*/
    boolean async() default true;
}
