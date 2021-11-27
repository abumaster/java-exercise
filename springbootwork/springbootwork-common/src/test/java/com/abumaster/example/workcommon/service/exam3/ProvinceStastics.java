package com.abumaster.example.workcommon.service.exam3;

import cn.hutool.core.util.NumberUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 按照省份统计
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class ProvinceStastics implements Runnable{
    /** 保存读到的数据*/
    private final List<PersonBean> readData;
    CountDownLatch downLatch;
    public ProvinceStastics(List<PersonBean> readData, CountDownLatch downLatch) {
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
        System.out.println("文件已经读取完成，开始按照省份统计...");
        Map<String, List<PersonBean>> collect = readData.parallelStream().collect(Collectors.groupingBy(PersonBean::getProvince));
        for (Map.Entry<String,List<PersonBean>> entry:collect.entrySet()) {
            double sum = entry.getValue().stream().mapToDouble(PersonBean::getSalary).sum();
            System.out.println(entry.getKey()+" :"+ NumberUtil.div(sum, entry.getValue().size()));
        }
    }
}
