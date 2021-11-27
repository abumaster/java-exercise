package com.abumaster.example.workcommon.service.exam3;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 读取文件
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
class ReadFileThread implements Runnable {
    /** 合法的长度*/
    private static final int LEN=7;
    /** 保存读到的数据*/
    private final List<PersonBean> readData;
    CountDownLatch downLatch;
    String path;
    public ReadFileThread(String path, List<PersonBean> readData, CountDownLatch downLatch) {
        this.path = path;
        this.readData = readData;
        this.downLatch = downLatch;
    }
    @Override
    public void run() {
        FileReader fileReader  = new FileReader(this.path,"GB2312");
        List<String> data = fileReader.readLines();
        data = data.subList(1,data.size());
        data.parallelStream().forEach(line->{
                PersonBean p = new PersonBean();
                String[] split = StringUtils.split(line, "\t");
                if (null != split && split.length == LEN) {
                    p.setName(split[0]);
                    p.setSex(split[1]);
                    p.setMobile(split[2]);
                    p.setAge(NumberUtil.parseInt(split[3]));
                    p.setSalary(NumberUtil.parseFloat(split[4]));
                    p.setProvince(split[5]);
                    p.setCity(split[6]);
                    readData.add(p);
                }
        });

        // 通知其他的线程开始执行
        downLatch.countDown();
    }
}