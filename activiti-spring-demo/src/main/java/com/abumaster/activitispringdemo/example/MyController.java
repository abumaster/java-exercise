package com.abumaster.activitispringdemo.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: activiti-spring-demo
 * @description: Controller
 * @author: zhang guofeng
 * @create: 2020-02-06 12:16
 */
@RestController
@Slf4j
@RequestMapping("/act")
public class MyController {
    @Resource
    MyService myService;

    @RequestMapping("/list")
    public List<MyTaskBean> listAllTask(@RequestParam(name = "assignee",required = false)String assignee){
        return myService.listAllTaskByAssignee(assignee);
    }

    @GetMapping("/deal")
    public String completeTask(@RequestParam(name="taskId")String taskId){
        myService.completeTask(taskId);
        return "success";
    }
}