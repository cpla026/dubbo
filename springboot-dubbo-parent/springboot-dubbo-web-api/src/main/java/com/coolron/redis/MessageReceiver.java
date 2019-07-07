package com.coolron.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: xf
 * @Date: 22018/11/20 23:15
 * @Description:  消息接收器
 */
@Slf4j
@Component
public class MessageReceiver {

    /**接收消息的方法*/
    public void receiveMessage(String msg){
       log.info("收到一条消息：{}", msg);
       Map<String, String> map = JSON.parseObject(msg, Map.class);
       String sessionId = map.get("sessionId");
       String message = map.get("message");
       log.info("sessionId : {}", sessionId);
       log.info("message : {}", message);
    }
}
