package com.abumaster.demo.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: demo
 * @description: 初始化activiti的表
 * @author: zhang guofeng
 * @create: 2020-02-04 10:22
 */
@SpringBootTest
public class ActivitiCreateTableTest {

    @Test
    public void initTable() {
        // 创建引擎配置类
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("123");
        // 如果表不存在则创建表
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        // 从配置信息中创建工作流的核心对象
        ProcessEngine engine = processEngineConfiguration.buildProcessEngine();
        System.out.println(engine);
    }

    @Test
    public void loadEngineFromCfg(){
        ProcessEngine engine= ProcessEngines.getDefaultProcessEngine();
    }
}