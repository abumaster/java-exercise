package com.abumaster.example.crud.transdemo.dao;

import com.abumaster.example.crud.transdemo.bean.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * dao
 *
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/6
 */
@Repository
public class UserInfoDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;


    public int insertOne(UserInfo userInfo) {
        String sql = "INSERT INTO `test`.`user_info`( `user_account`, `user_password`, `user_name`, `mobile`, `valid`, `user_type`) " +
                "VALUES (?,?,?,?,?,?);";
        return jdbcTemplate.update(sql, userInfo.getUserAccount(),userInfo.getUserPassword(),userInfo.getUserName(),
                userInfo.getMobile(),userInfo.getValid(),userInfo.getUserType());
    }

    public int insertTwo(String userName) {
        String sql = "insert into user(name) values(?);";
        return jdbcTemplate.update(sql,userName);
    }
}
