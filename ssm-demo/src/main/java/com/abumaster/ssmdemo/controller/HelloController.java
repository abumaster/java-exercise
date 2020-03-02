package com.abumaster.ssmdemo.controller;

import com.abumaster.ssmdemo.entity.UserInfo;
import com.abumaster.ssmdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public UserInfo getOneUser(@RequestParam(name = "uid")String uid){
        UserInfo userInfo=
         userService.getOne(uid);
        log.info(userInfo.toString());
        return userInfo;
    }
}
