package com.abumaster.ssmdemo.entity;


import lombok.Data;


import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private String uid;
    private String userName;
    private String passWord;
    private String email;
    private String updateTime;
    private String createTime;


    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

}
