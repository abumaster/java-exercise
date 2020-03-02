package com.abumaster.ssmdemo.service.impl;

import com.abumaster.ssmdemo.dao.UserInfoMapper;
import com.abumaster.ssmdemo.entity.UserInfo;
import com.abumaster.ssmdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getOne(String uid) {
        return userInfoMapper.selectOne(uid);
    }
}
