package com.abumaster.example.crud.transdemo.service;

import cn.hutool.core.thread.ThreadUtil;
import com.abumaster.example.crud.transdemo.bean.UserInfo;
import com.abumaster.example.crud.transdemo.dao.UserInfoDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/6
 */
@Service
@Slf4j
public class UserInfoService {

    @Resource
    private ServiceOne serviceOne;

    @Resource
    private ServiceTwo serviceTwo;

    /** required类型事务传播 正常的*/
    public void insertOneDemo() {
        serviceOne.addRequired();
        serviceTwo.addRequired();
    }
    /** 方法中有一个是异常的*/
    @Transactional(rollbackFor = Exception.class)
    public void insertWithException() {
        serviceTwo.addRequired();
        serviceOne.addRequiredException();
    }

    /** 测试1：外围方法没开启事务，并抛出异常 均会插入数据*/
    public void insertNoTransactionalRequiredNew() {
        serviceOne.addRequiredNew();
        serviceTwo.addRequiredNew();
        throw new RuntimeException();
    }
    /** 测试1：外围方法没开启事务，调用的业务方法中抛出异常*/
    public void insertNoTransactionalRequiredNewTwo() {
        serviceOne.addRequiredNew();
        // 自己的事务回退了不会插入
        serviceTwo.addRequiredNewException();
    }
    @Transactional(rollbackFor = Exception.class)
    public void insertTransactionalRequiredNew() {
        // 加入到调用者事务中 不会插入
        serviceOne.addRequired();
        // 自己新的事务 插入成功
        serviceOne.addRequiredNew();
        // 插入成功
        serviceTwo.addRequiredNew();
        throw new RuntimeException();
    }
    @Transactional(rollbackFor = Exception.class)
    public void insertTransactionalRequiredNewTwo() {
        // 加入到调用者事务中
        serviceOne.addRequired();
        serviceOne.addRequiredNew();
        // 自己的事务回退了不会插入，将异常往上抛，导致调用者事务也回退了
        serviceTwo.addRequiredNewException();
    }
    @Transactional(rollbackFor = Exception.class)
    public void insertTransactionalRequiredNewThree() {
        // 加入到调用者事务中
        serviceOne.addRequired();
        serviceOne.addRequiredNew();
        try {
            serviceTwo.addRequiredNewException();
        } catch (Exception e) {
            log.error("捕获异常：{}",e.toString());
        }

    }

}
