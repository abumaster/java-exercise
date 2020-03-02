package com.abumaster.springbootdemocrud.bookstore.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.abumaster.springbootdemocrud.bookstore.mapper.UserMapper;
import com.abumaster.springbootdemocrud.bookstore.domain.User;
import com.abumaster.springbootdemocrud.bookstore.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(String uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(User record) {
        //添加对应的字段

        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int checkUserName(String userName) {
        if(userMapper.selectOneByUserName(userName)!=null) {
            return 1;
        }else{
            return -1;
        }
    }

    /**检查用户名和密码是否符合**/
    @Override
    public int checkUserNameAndPassword(String userName, String password) {
        if(userMapper.selectOneByUserNameAndPassWord(userName,password)!=null){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public User getOneUserByName(String userName) {
        return userMapper.selectOneByUserName(userName);
    }
}
