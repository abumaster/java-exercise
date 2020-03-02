package com.abumaster.websocketdemo.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 服务的开启
 * @author zgh
 */
@Component
@ServerEndpoint("/imserver/{userId}")
@Slf4j
public class WebSocketServer {
    /**
     * 在线的数量
     */
    private static Integer onlineCount=0;

    /**存放连接的对象*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap=new ConcurrentHashMap<>();
    /**会话session用于发送数据**/
    private Session session;
    private String userId="";

    /**
     * 连接成功时触发
     * @param session 会话
     * @param userId 传递的参数
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId){
        this.session=session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
        }else{
            webSocketMap.put(userId,this);
            addOnlineCount();
        }
        log.info("用户{}连接，当前在线人数：{}",userId,getOnlineCount());
        try {
            //发送消息
            sendMessage(new Date().toString()+" "+userId+",连接成功!");
        }catch (Exception e) {
            log.error("用户{}异常：{}",userId,e.toString());
        }
    }
    @OnClose
    public void onClose(){
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:{},当前在线人数为:{}",userId,getOnlineCount());
    }

    /**
     * 收到客户端的消息后调用的方法
     * @param msg 消息 json格式
     * @param session
     */
    @OnMessage
    public void onMessage(String msg,Session session){
        log.info("用户：{}发送的报文:{}",userId,msg);
        if(StringUtils.isNotEmpty(msg)){
            try{
                JSONObject jsonObject = JSON.parseObject(msg);
                //追加发送人
                jsonObject.put("fromUserId",userId);
                //取出目标人
                String toUserId = jsonObject.getString("toUserId");
                if(StringUtils.isNotEmpty(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else {
                    log.error("请求的用户:{}不在该服务器上",toUserId);
                }
            }catch (Exception e){
                log.error("{}",e.toString());
            }
        }
    }

    @OnError
    public void onError(Throwable error){
        log.error("用户{}，连接出错：{}",userId,error.toString());
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param msg 消息
     * @throws IOException 处理异常
     */
    private void sendMessage(String msg)throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:"+userId+"，报文:"+message);
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            log.error("用户"+userId+",不在线！");
        }
    }
    public static synchronized Integer getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }
}
