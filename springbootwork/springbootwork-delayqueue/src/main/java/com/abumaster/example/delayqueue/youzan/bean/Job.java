package com.abumaster.example.delayqueue.youzan.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 放入队列的job
 * 消息结构
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Data
public class Job implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /** 业务类型*/
    private String topic;
    /** 延迟时间，转换成绝对秒*/
    private long delay;
    /** job的执行时间*/
    private long ttr;
    /** 具体的内容，转换成json*/
    private String body;
    /** 重试时间*/
    private int retryCount;
    /** 任务状态 0:*/
    private int status;


    @Getter
    public enum JobStatus {
        /** 就绪*/
        READY(1,"就绪"),
        DELAY(0,"不可执行"),
        RESERVED(2,"已被消费者读取，但还未得到消费者的响应"),
        FINISHED(3,"完成"),
        DELETED(-1, "删除");

        int s;
        String msg;
        JobStatus(int s,String msg) {
            this.s=s;
            this.msg=msg;
        }

    }

}
