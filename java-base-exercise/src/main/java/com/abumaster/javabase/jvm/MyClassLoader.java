package com.abumaster.javabase.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLClassLoader;

/**
 * 自定义的类加载器
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/2/13
 */
public class MyClassLoader extends ClassLoader {
    /**
     * 自己实现类的查找逻辑
     *
     * @param name 名称
     * @return /
     * @throws ClassNotFoundException 未找到
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String resource = "E:/programCode/work";
        File source = new File(resource,name.replace(".","/").concat(".class"));
        if(!source.exists()) {
            throw new ClassNotFoundException(name+" not found!");
        }
        try(FileInputStream fileInputStream = new FileInputStream(source)) {
            System.out.println("自定义类加载器读取类文件...");
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            return super.defineClass(name,bytes,0,bytes.length);
        }catch (Exception e) {
            System.out.println("class not found!");
        }
        return super.findClass(name);
    }
}
