package com.abumaster.example.workconfig.task;

import com.abumaster.example.workconfig.bean.AreaInfo;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 读取json的数据
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/8/21
 */
@Service
@Slf4j
public class ReadJsonService {
    /** 读取整个文件的内容*/
    @ApolloConfig("area-info.json")
    private Config jsonConfig;

    @ApolloJsonValue("${content:[]}")
    private List<AreaInfo> areaInfoList;

    @Value("${content}")
    private String content;

    @PostConstruct
    public void init() {
        log.info("==========================:{}", content);
       log.info("字符串:{}", jsonConfig.getProperty("content","[]"));
        log.info("读取json的数据为:{}", areaInfoList);
        log.info(jsonConfig.getSourceType().getDescription());
    }
}
