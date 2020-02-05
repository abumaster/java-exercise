package com.abumaster.demo.common;

/**
 * @program: demo
 * @description: 通用的返回对象
 * @author: zhang guofeng
 * @create: 2020-02-01 16:02
 */
public class Response {
    private Response(){}
    private int code;
    private String msg;
    private Object data;

    private static int SUCCESS=0;
    private static int FAILD=-1;
    private static String SUCCESS_MSG="操作成功";
    private static String FAILD_MSG="操作失败";

    private Response(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static Response ok(){
        return new Response(SUCCESS,SUCCESS_MSG,null);
    }
    public static Response ok(Object data){
        return new Response(SUCCESS,SUCCESS_MSG,data);
    }
    public static Response ok(String msg,Object data){
        return new Response(SUCCESS,msg,data);
    }

    public static Response error(){
        return new Response(FAILD,FAILD_MSG,null);
    }
    public static Response error(String msg){
        return new Response(FAILD,msg,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}