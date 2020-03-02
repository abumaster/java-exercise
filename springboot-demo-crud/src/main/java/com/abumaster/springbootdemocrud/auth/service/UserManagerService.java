package com.abumaster.springbootdemocrud.auth.service;

import com.abumaster.springbootdemocrud.auth.dto.UserDto;
import com.abumaster.springbootdemocrud.bookstore.domain.User;
import com.abumaster.springbootdemocrud.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 用户管理的服务层
 * @author zgh
 */
@Service
@Slf4j
public class UserManagerService {
    @Resource(name = "userServiceImpl")
    UserService userService;

    /**
     * 注册新的用户
     * @param userDto 注册对象
     * @return -1失败
     */
    public int registerUser(UserDto userDto) {
        int flag=0;
        User user = new User();
        user.setUid(UUID.randomUUID().toString());
        user.setCreateTime(String.valueOf(System.currentTimeMillis()));
        user.setEmail(userDto.getEmail());
        user.setPassWord(userDto.getUserPassword());
        user.setUpdateTime(String.valueOf(System.currentTimeMillis()));
        user.setUserName(userDto.getUserName());
        if(userService.checkUserName(userDto.getUserName())>0){
            flag=-1;
        }else {
            flag = userService.insert(user);
        }
        return flag;
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 密码
     * @return 1成功 -1失败
     */
    public int userLogin(String userName,String password){
        if(userService.checkUserNameAndPassword(userName,password)>0) {
            return 1;
        }else{
            return -1;
        }
    }

    public User getUserByName(String userName){
        return userService.getOneUserByName(userName);
    }

}
