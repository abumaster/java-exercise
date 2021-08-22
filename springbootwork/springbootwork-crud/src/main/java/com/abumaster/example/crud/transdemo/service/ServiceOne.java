package com.abumaster.example.crud.transdemo.service;

import com.abumaster.example.crud.transdemo.bean.UserInfo;
import com.abumaster.example.crud.transdemo.dao.UserInfoDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/7
 */
@Service
public class ServiceOne {

    @Resource
    private UserInfoDAO userInfoDAO;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addRequiredException(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount("wangwu");
        userInfoDAO.insertOne(userInfo);
        long np = userInfo.getValid().longValue();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequired() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount("wangwu");
        userInfoDAO.insertOne(userInfo);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount("wangwu");
        userInfoDAO.insertOne(userInfo);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void addNested() {

    }
}
