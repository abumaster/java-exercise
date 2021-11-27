package com.abumaster.example.crud.server.bean;

import lombok.Data;

import java.util.Date;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/29
 */
@Data
public class Person {
    private Long id;
    private String name;
    private Date date;
}
