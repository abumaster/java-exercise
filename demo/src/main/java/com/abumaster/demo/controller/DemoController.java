package com.abumaster.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: demo
 * @description: Demo
 * @author: zhang guofeng
 * @create: 2020-02-01 10:09
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/one")
    public void testRequest(HttpServletRequest request,
                            HttpServletResponse response)throws Exception{
        // 向浏览器输出流的数据，二进制数据
        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        servletOutputStream.write("1234-这个还是".getBytes());
    }
    @RequestMapping("/two")
    public void testRequestTwo(HttpServletRequest request,
                               HttpServletResponse response)throws Exception{
        // 向浏览器输出字符数据
        // 设置内容编码为utf-8
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("测试二：中文的显示!hhhh");
    }

    @RequestMapping("/three")
    public void testRefreshThree(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception{
        //
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("3s后跳转页面......");
        // 设置refresh3s后
        response.setHeader("Refresh","30;url='/demo/hello'");
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String helloDemo()
    {
        return "Hello World 这是一个测试";
    }
}