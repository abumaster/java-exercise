package com.abumaster.springbootdemocrud.common;

import java.io.Serializable;

public class Response implements Serializable {

    /**返回码**/
    private Integer code;
    /**返回消息**/
    private String msg;
    /**返回数据**/
    private Object data;

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private static Integer ERROR_CODE=-1;
    private static Integer SUCCE_SSCODE=0;
    private static String ERROR_MSG="操作失败!";
    private static String SUCCESS_MSG="操作成功!";

    public static Response ok(){
        return new Response(SUCCE_SSCODE,SUCCESS_MSG,null);
    }

    public static Response ok(Object data){
        return new Response(SUCCE_SSCODE,SUCCESS_MSG,data);
    }

    public static Response error(){
        return new Response(ERROR_CODE,ERROR_MSG,null);
    }

    public static Response error(String msg) {
        return new Response(ERROR_CODE,msg,null);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
