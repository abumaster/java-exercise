package com.abumaster.activitispringdemo.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: activiti-spring-demo
 * @description: person实体
 * @author: zhang guofeng
 * @create: 2020-02-06 11:56
 */
@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String birthDate;

}