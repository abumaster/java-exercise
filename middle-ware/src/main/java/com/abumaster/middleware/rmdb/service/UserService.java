package com.abumaster.middleware.rmdb.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.abumaster.middleware.rmdb.bean.UserInfo;
import com.abumaster.middleware.rmdb.bean.VisitorLog;
import com.abumaster.middleware.rmdb.mapper.LogInfoMapper;
import com.abumaster.middleware.rmdb.mapper.UserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 功能详细描述
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2020/10/20
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private LogInfoMapper logInfoMapper;


    @Transactional(rollbackFor = Exception.class)
    public void addUser(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount("zgf");
        userInfo.setUserName("张国丰");
        userInfo.setUserPassword("123457");
        userInfo.setMobile("18271419197");
        userInfo.setValid(1);
        userInfo.setUserType("admin");
        userInfoMapper.insert(userInfo);
//        try {
//            // 如果使用this调用会使这个方法调用失败
//            addLog("zgf");
//        }catch (Exception e){
//            log.error("{}",e.toString());
//        }
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void addLog(String userAccount){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        VisitorLog visitorLog = new VisitorLog();
        visitorLog.setName(userInfo.getUserName());
        visitorLog.setMsg("访问了");
        visitorLog.setUpdateTime(DateUtil.currentSeconds());
        visitorLog.setInsertDate(new Date());

        logInfoMapper.insert(visitorLog);
        int i=1/0;
    }
}
