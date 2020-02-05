package com.abumaster.demo.controller;

import com.abumaster.demo.common.Response;
import com.abumaster.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: session技术
 * 保存到服务器上的浏览器状态机制，可存储对象
 *
 * @author: zhang guofeng
 * @create: 2020-02-01 17:21
 */
@Controller
@Slf4j
@RequestMapping("/session")
public class SessionDemo {
    /**
     *     登录用户Session 的key
     */
    private static String SESSION_KEY="loginUser";

    @RequestMapping
    public Response index(HttpServletRequest request){
        // 首先判断是否已经有用户登录
        HttpSession session = request.getSession();
        if(null==session.getAttribute(SESSION_KEY)) {
            return Response.error("用户未登录,请先登录 /login");
        }
        // 取出用户的信息
        try {
            User user = (User)session.getAttribute(SESSION_KEY);
            log.info("用户：{} 已经登录",user.getUserName());
            return Response.ok("用户已经登录",user);
        }catch (Exception e){
            log.error("user对象转换失败:{}",e.toString());
            return Response.error();
        }
    }

    /** 设置session
     * @param request request
     * @return 成功
     */
    @RequestMapping("/login")
    public Response sessionDemo(HttpServletRequest request){
        User user = new User();
        user.setUserName("张国丰");
        user.setUserPassword("123456");
        // 登录后设置session
        request.getSession().setAttribute(SESSION_KEY,user);
        return Response.ok("登录成功",user);
    }


}