package com.abumaster.myself.service;


import com.abumaster.myself.bean.AuthorInfoBean;

/**
 * 提供的服务
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
public class AuthorService {

    private AuthorInfoBean authorInfoBean;

    public AuthorService(AuthorInfoBean authorInfoBean) {
        this.authorInfoBean = authorInfoBean;
    }

    public AuthorService() {
    }

    /** 对外提供的服务*/
    public String printAuthor() {
        System.out.println(authorInfoBean.toString());
        return authorInfoBean.toString();
    }
}
