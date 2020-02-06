package com.abumaster.activitispringdemo.example;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: activiti-spring-demo
 * @description: dao层
 * @author: zhang guofeng
 * @create: 2020-02-06 11:59
 */
public interface PersonRepository  extends JpaRepository<Person,Integer> {
    /**
     * 根据用户的名称查找用户的信息
     * @param userName 用户名
     * @return Person实体
     */
    Person findByUserName(String userName);
}