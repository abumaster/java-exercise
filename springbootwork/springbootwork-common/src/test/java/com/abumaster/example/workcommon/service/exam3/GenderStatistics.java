package com.abumaster.example.workcommon.service.exam3;

import cn.hutool.core.util.NumberUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class GenderStatistics implements Runnable{

    /** 保存读到的数据*/
    private final List<PersonBean> readData;
    CountDownLatch downLatch;
    Map<String,String> result = new HashMap<>();
    public GenderStatistics(List<PersonBean> readData, CountDownLatch downLatch) {
        this.readData = readData;
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("文件已经读取完成，开始按照年龄段统计工资...");
        Map<Integer,List<Float>> temp = new HashMap<>();
        for(PersonBean person: readData) {
            int k = person.getAge()/10;
            if (!temp.containsKey(k)) {
                temp.put(k, new ArrayList<>());
            }
            temp.get(k).add(person.getSalary());
        }
        // 转换成时间段
        for(Map.Entry<Integer,List<Float>> entry: temp.entrySet()) {
            Integer key = entry.getKey();
            String s = String.format("%d-%d", 10*key, 10*(key+1));
            String value = String.valueOf(NumberUtil.div(entry.getValue().stream().mapToDouble(item -> item).sum(), entry.getValue().size()));
            System.out.println(s+": "+value);
        }
    }
}
