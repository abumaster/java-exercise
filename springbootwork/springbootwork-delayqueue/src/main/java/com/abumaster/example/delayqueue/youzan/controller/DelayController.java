package com.abumaster.example.delayqueue.youzan.controller;

import com.abumaster.example.delayqueue.youzan.bean.DelayJob;
import com.abumaster.example.delayqueue.youzan.bean.Job;
import com.abumaster.example.delayqueue.youzan.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("delay")
public class DelayController {
    
    @Autowired
    private JobService jobService;

    private static final AtomicInteger index = new AtomicInteger(0);

    private static final String[] tag = new String[]{"test","web","java"};


    /**
     * 添加 测试的时候使用
     * @return
     */
    @RequestMapping(value = "addTest",method = RequestMethod.GET)
    public DelayJob addDefJobTest() {
        Job request = new Job();
        int i = index.addAndGet(1);
        Long aLong = Long.valueOf(i);
        request.setId(aLong);
        int num = i%3;
        request.setTopic(tag[num]);
        request.setBody("tag:" + tag[num] + "id:" + i);
        request.setDelay(10);
        request.setTtr(10);
        return jobService.addJob(request);

    }

    /**
     * 添加
     * @param request
     * @return
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public DelayJob addDefJob(Job request) {
        DelayJob delayJob = jobService.addJob(request);
        return delayJob;
    }

    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "pop/{topic}",method = RequestMethod.GET)
    public Job getProcessJob(@PathVariable("topic") String topic) {
        Job process = jobService.getReadyJob(topic);
        List<Job> list = new ArrayList<>();
        return process;
    }

    /**
     * 完成一个执行的任务
     * @param jobId
     * @return
     */
    @RequestMapping(value = "finish",method = RequestMethod.DELETE)
    public String finishJob(Long jobId) {
        jobService.finishJob(jobId);
        return "success";
    }
    /** 查找x元素所在位置的索引  有序数组*/
    public static int findElement(int[] arr, int start, int end,  int x) {
        if (start>end) {
            return -1;
        }
        int mid = (start+end)/2;
        if (arr[mid]==x) {
            return mid;
        } else if(arr[mid]>x) {
            // 往左查找
            return findElement(arr, start, mid-1, x);
        } else {
            // 往右查找
            return findElement(arr, mid+1, end, x);
        }
    }

    public static void main(String[] args) {
       // int
        int[] arr=new int[]{2,3,4,5,6,9,10,11,12,13};
        System.out.println(findElement(arr,0,9, 100));

    }
}