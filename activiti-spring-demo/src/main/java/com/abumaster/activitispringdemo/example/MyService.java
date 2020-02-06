package com.abumaster.activitispringdemo.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: activiti-spring-demo
 * @description: activiti服务
 * @author: zhang guofeng
 * @create: 2020-02-06 11:54
 */
@Service
@Slf4j
public class MyService {
    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @Resource
    HistoryService historyService;

    @Resource
    PersonRepository personRepository;

    /**
     * 启动任务
     * @param assignee 分配人
     */
    public void startProcess(String assignee){
        Person person=personRepository.findByUserName(assignee);
        //设置流程的参数
        Map<String,Object> var=new HashMap<>();
        var.put("person",person);
        runtimeService.startProcessInstanceByKey("oneTaskProcess",var);
    }


    public List<MyTaskBean> listAllTaskByAssignee(String s){
        List<Task> tasks= new ArrayList<>();
        List<MyTaskBean> results=new ArrayList<>();
        if(StringUtils.isEmpty(s)) {
            tasks= taskService.createTaskQuery().list();
        }else {
            tasks = taskService.createTaskQuery().taskAssignee(s).list();
        }
        for(Task task:tasks) {
            MyTaskBean taskBean = new MyTaskBean();
            taskBean.setTaskId(task.getId());
            taskBean.setTaskName(task.getName());
            taskBean.setStart(task.getCreateTime().toString());
            taskBean.setAssigner(task.getAssignee());
            taskBean.setEnd(task.getProcessInstanceId());
            results.add(taskBean);
        }
        return results;
    }

    /**
     * 完成任务
     * @param taskId 任务id
     */
    public void completeTask(String taskId){
        taskService.complete(taskId);
    }

    public List<HistoricTaskInstance> findHistory(String assignee) {
        return historyService.createHistoricTaskInstanceQuery().finished().list();
    }
}