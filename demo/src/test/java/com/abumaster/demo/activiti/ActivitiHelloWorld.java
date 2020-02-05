package com.abumaster.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: demo
 * @description: 流程的部署测试
 * 一个流程定义文件可以生成多个流程实例；
 * 一个流程实例中包含多个任务执行节点，
 * @author: zhang guofeng
 * @create: 2020-02-04 11:36
 */
@SpringBootTest
@Slf4j
public class ActivitiHelloWorld {
    /**
     * 部署流程定义，从指定的bpmn文件中
     */
    @Test
    public void deployProcessDefine(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService()
                //防止标签检查失败，需要把验证关闭disableSchemaValidation
                .createDeployment().disableSchemaValidation().name("测试Hello").addClasspathResource("diagram/helloworld.bpmn")
                .deploy();
        //id2501
        log.info("流程:{},发布成功id:{}",deployment.getName(),deployment.getId());
    }

    /**
     * 启动流程实例只有一个，执行对象可以有多个
     */
    @Test
    public void startProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstance processInstance=processEngine.getRuntimeService()
                .startProcessInstanceByKey("myProcess_1");
//        RuntimeService runtimeService=processEngine.getRuntimeService();
        log.info("启动流程实例成功：{}",processInstance.getId());
        log.info("流程:{},定义id:{}",processInstance.getName(),processInstance.getProcessDefinitionId());
    }

    /**
     * 查询流程定义，从文件中发布后的流程，按版本存储
     */
    @Test
    public void findProcessDefinition(){
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list=processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        if(null!=list && !list.isEmpty()) {
            for(ProcessDefinition p:list){
                log.info("流程id：{}  流程名称：{}  流程定义key：{}\n" +
                        "流程定义版本：{}  资源bpm文件：{} \n" +
                        "资源png文件：{}  部署对象id：{}",p.getId(),
                        p.getName(),p.getKey(),p.getVersion(),p.getResourceName(),
                        p.getDiagramResourceName(),p.getDeploymentId());
            }
        }
    }

    @Test
    public void findMyPersonalTask(){
        String name="张三";
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        List<Task> list=processEngine.getTaskService().createTaskQuery()
//                .taskAssignee(name)
                .orderByTaskCreateTime().asc()
                .list();
        if(null!=list&& !list.isEmpty()){
            for(Task task:list){
                log.info("任务id：{}  任务名称：{}",task.getId(),task.getName());
            }
        }
    }

    /**
     * 完成我的任务
     */
    @Test
    public void completePersonalTask(){
        String taskId="5004";
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete(taskId);
        log.info("完成任务：{}",taskId);
    }

}