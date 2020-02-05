package com.abumaster.demo.controller;

import com.abumaster.demo.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: demo
 * @description: Cookie技术使用
 * cookie 保存在浏览器中，也可以持久化到硬盘上，每次浏览器请求时会带上
 * 具有域名隔离的性质
 * @author: zhang guofeng
 * @create: 2020-02-01 15:55
 */
@RestController
@RequestMapping("/cookie")
public class CookieDemo {
    private Logger logger = LoggerFactory.getLogger(CookieDemo.class);

    /**
     * 设置cookie 到response中
     */
    @RequestMapping("/one")
    public String cookieDemoOne(HttpServletRequest request,
                                HttpServletResponse response){
        // s设置cookie
        Cookie cookie = new Cookie("username","zhangguofeng");
        // 设置cookie的时间
        cookie.setMaxAge(1000);
        response.addCookie(cookie);
        return "成功设置Cookie";
    }

    @RequestMapping("/read")
    public Response cookieReadDemo(HttpServletRequest request){
        // 从请求中读取cookie
        Cookie[] cookes=request.getCookies();
        List<Map<String,Object>> data=new ArrayList<>();
        for(Cookie c:cookes){
            logger.info("Cookie名称:{}",c.getName());
            Map<String,Object> item=new HashMap<>();
            item.put("name",c.getName());
            item.put("value",c.getValue());
            item.put("domain",c.getDomain());
            data.add(item);
        }
        return Response.ok(data);
    }

    @RequestMapping("/add")
    public Response cookieAddDemo(HttpServletResponse response)throws Exception{
        // 如果cookie中要设置中文，需要编码，或者转换成加密后的字符串
        Cookie cookie=new Cookie("uid", URLEncoder.encode("中文","utf-8"));
        response.addCookie(cookie);
        return Response.ok();
    }

}