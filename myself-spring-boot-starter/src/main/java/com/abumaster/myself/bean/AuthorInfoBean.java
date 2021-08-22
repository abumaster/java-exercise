package com.abumaster.myself.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * bean 读取配置文件中的值
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
@ConfigurationProperties(prefix = "myself.author")
public class AuthorInfoBean {
    private String name;
    private String tel;
    private String address;
    private String sex="M";

    @Override
    public String toString() {
        return "作者信息：{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
