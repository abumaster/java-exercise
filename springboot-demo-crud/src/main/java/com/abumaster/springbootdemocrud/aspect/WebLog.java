package com.abumaster.springbootdemocrud.aspect;

import java.lang.annotation.*;

/**
 * 日志注解，用于统一打印日志
 * @author zgh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    /**日志记录的描述**/
    String description() default "";
}
