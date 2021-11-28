package com.abumaster.example.eventbus.simple.event;

import lombok.Data;

import java.util.Date;

/**
 * 订单事件
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/11/28
 */
@Data
public class OrderEvent {
    /** 订单ID*/
    private Long id;
    /** 用户id*/
    private String userId;
    /** 描述*/
    private String desc;
    /** 订单创建时间*/
    private Date date;

}
