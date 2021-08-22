package com.abumaster.javabase.jvm;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/13
 */
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass("com.abumaster.javabase.jvm.LoadDemo");
            System.out.println("类名称："+aClass.getName());
            System.out.println("类加载器："+aClass.getClassLoader());

            // 实例化一个对象i
            Object o = aClass.getConstructors()[0].newInstance();
            // 调用print方法方法
            aClass.getMethod("print",String.class).invoke(o,"what?");


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
