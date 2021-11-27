package com.abumaster.example.workcommon.service.exam3;

/**
 * 人员的bean
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/9/11
 */
public class PersonBean {
    /** 姓名*/
    private String name;
    /** 性别*/
    private String sex;
    private String mobile;
    private Integer age;
    /** 工资*/
    private Float salary;
    /** 省份*/
    private String province;
    /** 城市*/
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
