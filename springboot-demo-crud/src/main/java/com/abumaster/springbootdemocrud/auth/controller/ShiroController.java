package com.abumaster.springbootdemocrud.auth.controller;

import com.abumaster.springbootdemocrud.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "页面跳转")
public class ShiroController {

    @GetMapping("/login")
    public Response pleaseLogin(){
        return Response.ok("请登录系统");
    }
    @GetMapping("/index")
    @ApiOperation("首页")
    public Response indexPage(){
        return Response.ok("登录成功，这是首页");
    }

    @GetMapping("/unauthorizedurl")
    @ApiOperation("未认证")
    public Response unauthPage(){
        return Response.ok("未认证");
    }
}
