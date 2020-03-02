package com.abumaster.springbootdemocrud.auth.controller;

import com.abumaster.springbootdemocrud.aspect.WebLog;
import com.abumaster.springbootdemocrud.auth.dto.UserDto;
import com.abumaster.springbootdemocrud.auth.service.UserManagerService;
import com.abumaster.springbootdemocrud.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户的管理
 * @author zgh
 */
@RestController
@Slf4j
@RequestMapping("/api/manager/user")
@Api(tags = "用户管理")
public class UserManagerController {

    @Resource
    UserManagerService userManagerService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Response login(HttpSession httpSession,
                          @RequestBody UserDto userDto) {
        log.info("登录用户名:{},密码:{}",userDto.getUserName(),userDto.getUserPassword());
        try {
           Subject subject = SecurityUtils.getSubject();
           subject.login(new UsernamePasswordToken(userDto.getUserName(),userDto.getUserPassword()));
           log.info("登录成功!");
           return Response.ok("登录成功!");
        }catch (IncorrectCredentialsException | UnknownAccountException e) {
            log.error("登录错误:{}",e.toString());
            return Response.error(e.toString());
        }
    }
    @PostMapping("/register")
    @ApiOperation("用户注册")
    @WebLog(description = "用户注册")
    public Response registerUser(HttpSession httpSession,
                                 @RequestBody UserDto userDto) {
        int flag = userManagerService.registerUser(userDto);
        if(flag>0) {
            return Response.ok(userDto);
        }else {
            return Response.error();
        }
    }
    @GetMapping("/check")
    public Response checkUser(HttpSession httpSession) {
        UserDto userDto = (UserDto)httpSession.getAttribute("userInfo");
        if(null==userDto) {
            return Response.error();
        }else{
            return Response.ok(userDto);
        }
    }

}
