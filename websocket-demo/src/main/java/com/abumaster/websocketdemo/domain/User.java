package com.abumaster.websocketdemo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    public String uid;

    public String userName;
    public String passWord;
    public String email;
    public String updateTime;
    public String createTime;

}
