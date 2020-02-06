package com.abumaster.activitispringdemo.example;

import lombok.Data;

/**
 * @program: activiti-spring-demo
 * @description: bean task
 * @author: zhang guofeng
 * @create: 2020-02-06 12:33
 */
@Data
public class MyTaskBean {
    private String taskId;
    private String taskName;
    private String start;
    private String end;
    private String assigner;
}