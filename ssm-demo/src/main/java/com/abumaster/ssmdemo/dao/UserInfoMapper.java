package com.abumaster.ssmdemo.dao;

import com.abumaster.ssmdemo.entity.UserInfo;
import org.apache.ibatis.annotations.Param;


public interface UserInfoMapper {
    UserInfo selectOne(@Param("uid") String uid);

}
