package com.abumaster.example.crud.transdemo.service;

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
public class ServiceTwo {
    @Resource
    private UserInfoDAO userInfoDAO;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRequired() {
        userInfoDAO.insertTwo("name");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNew() {
        userInfoDAO.insertTwo("name");
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addRequiredNewException() {
        userInfoDAO.insertTwo("name");
        throw new RuntimeException("抛出异常");
    }
}
