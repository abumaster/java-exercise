package com.abumaster.example.workcommon.service.exam3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 读取文件并进行一些统计
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class JavaExam3 {

    private static List<PersonBean> data = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String path = "C:\\Users\\zgf\\Desktop\\2021技能评估Java补考\\2020中国居民收入统计.txt";
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 读取文件
        executorService.execute(new ReadFileThread(path, data,countDownLatch));
        // 按照省份统计平均收入
        executorService.execute(new ProvinceStastics(data, countDownLatch));
        // 按照性别统计平均收入
        executorService.execute(new GenderStatistics(data, countDownLatch));

        executorService.execute(new AgeStatistics(data, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int [] nums = new int[10];
        executorService.shutdown();

    }



}
