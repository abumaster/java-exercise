package com.abumaster.demo.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: demo
 * @description: activiti服务
 * @author: zhang guofeng
 * @create: 2020-02-04 10:54
 */
@SpringBootTest
@Slf4j
public class ActivitiServiceTest {

    @Test
    public void getActivitiServers(){
        // 首先从配置文件中加载默认引擎
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        log.info("默认引擎:{}",processEngine.getName());

        //管理流程定义
        RepositoryService repositoryService =processEngine.getRepositoryService();
        // 执行管理，任务的启动、推进、删除流程
        RuntimeService runtimeService=processEngine.getRuntimeService();
        // 任务管理
        TaskService taskService=processEngine.getTaskService();
        // 历史管理
        HistoryService historyService=processEngine.getHistoryService();
        // 组织机构管理
        IdentityService identityService=processEngine.getIdentityService();
        // 可选服务，任务表单管理
        FormService formService=processEngine.getFormService();
        ManagementService managementService=processEngine.getManagementService();
    }
}