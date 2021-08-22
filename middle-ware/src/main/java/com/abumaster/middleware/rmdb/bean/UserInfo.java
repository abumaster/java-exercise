package com.abumaster.middleware.rmdb.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户基础信息表
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2020/10/20
 */
@Data
@TableName("user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userAccount;
    private String userName;
    private String userPassword;
    private String mobile;
    private Integer valid;
    private String userType;

}
