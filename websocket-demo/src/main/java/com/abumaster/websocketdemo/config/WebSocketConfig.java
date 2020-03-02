package com.abumaster.websocketdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * web socket 的配置 开启websocket的支持
 * @author zgh
 */
@Configuration
public class WebSocketConfig {

    /**
     * 服务器端点，如果内嵌的tomcat则需要
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
