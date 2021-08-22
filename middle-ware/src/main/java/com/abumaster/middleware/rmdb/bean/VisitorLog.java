package com.abumaster.middleware.rmdb.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2020/10/20
 */
@Data
public class VisitorLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String msg;
    private Long updateTime;
    private Date insertDate;
}
