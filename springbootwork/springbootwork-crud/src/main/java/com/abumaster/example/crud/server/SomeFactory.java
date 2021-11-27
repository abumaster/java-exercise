package com.abumaster.example.crud.server;

import com.abumaster.example.crud.server.bean.Person;
import org.springframework.beans.factory.ObjectFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 几种factory的作用
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
public class SomeFactory {


    public void objFactory() {
        ObjectFactory<Person> objectFactory =()->{
            System.out.println("进行一些初始化工作");
            return new Person();
        };

        System.out.println(objectFactory.getObject());
    }

    public static void main(String[] args) {
        new SomeFactory().objFactory();
        String s1 = "helloword";
        String s2 = new String("helloword");
        String s3 = "hello"+"word";

        System.out.println(s1==s2);
        System.out.println(s1==s3);
        System.out.println(s1==s2.intern());


        // java1.8之后
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.plusDays(-1);
        System.out.println("今天："+now.format(formatter));
        System.out.println("昨天："+yesterday.format(formatter));

        // 1.8之前
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date yesterday2 = calendar.getTime();
        System.out.println(yesterday2);

    }
}
