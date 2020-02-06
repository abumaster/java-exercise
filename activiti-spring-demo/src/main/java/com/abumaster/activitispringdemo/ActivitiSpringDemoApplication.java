package com.abumaster.activitispringdemo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ActivitiSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiSpringDemoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner init(final RepositoryService repositoryService,
//								  final RuntimeService runtimeService,
//								  final TaskService taskService) {
//
//		return new CommandLineRunner() {
//			@Override
//			public void run(String... strings) throws Exception {
//				System.out.println("Number of process definitions : "
//						+ repositoryService.createProcessDefinitionQuery().count());
//				System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
//				runtimeService.startProcessInstanceByKey("oneTaskProcess");
//				System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
//			}
//		};
//	}

}
