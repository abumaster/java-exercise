package com.abumaster.javabase.juc;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReflectUtil;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * juc
 * CAS:比较和替换 是一条CPU原语，一条特殊的指令，用于多处理器对共享数据的访问。<br/>
 *  通过native方法实现<br/>
 *  unsafe只能让引导类加载器加载的类使用，
 *  可以通过Java命令行命令 -Xbootclasspath/a
 *  把调用 Unsafe 相关方法的类A所在 jar 包路径追加到默认的 bootstrap 路径中，
 *  使得A被引导类加载器加载，从而通过Unsafe.getUnsafe方法安全的获取 Unsafe 实例。
 *  也可以通过反射的方法实现。
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/9
 */
public class CasCode {

    /**
     * 通过反射获取unsfafe 实例
     * @return Unsafe
     */
    private static Unsafe getUnsafeInstance() {
        try {
            // 1.首先获取成员变量
            Field field = ReflectUtil.getField(Unsafe.class, "theUnsafe");
            // 2.设置权限
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }catch (SecurityException|IllegalArgumentException |IllegalAccessException e) {
            Console.error(e.toString());
            return null;
        }
    }

    private static void testDemo1() {
        Unsafe unsafeInstance = CasCode.getUnsafeInstance();
        if (null == unsafeInstance) {
            Console.error("获取unsafe失败！");
            return;
        }

        Console.log(unsafeInstance.addressSize());
    }

    public static void main(String[] args) {
        testDemo1();
    }

}
