package com.abumaster.example.workconfig.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 一个简单的bean
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
@Data
@ToString
public class AreaInfo {
    private String id;
    private String name;
    private List<AreaInfo> child;


}
