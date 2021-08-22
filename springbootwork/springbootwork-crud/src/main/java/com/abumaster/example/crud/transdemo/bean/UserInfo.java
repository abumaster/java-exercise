package com.abumaster.example.crud.transdemo.bean;

import lombok.Data;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/6
 */
@Data
public class UserInfo {
    private Long id;
    private String userAccount;
    private String userPassword;
    private String userName;
    private String mobile;
    private String userType;
    private Integer valid;


}
