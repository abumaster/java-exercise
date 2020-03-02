package com.abumaster.ssmdemo.service;

import com.abumaster.ssmdemo.entity.UserInfo;

public interface UserService {
    UserInfo getOne(String uid);
}
