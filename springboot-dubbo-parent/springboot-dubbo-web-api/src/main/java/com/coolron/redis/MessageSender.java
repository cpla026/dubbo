package com.coolron.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: xf
 * @Date: 2018/11/20 23:10
 * @Description:  消息发送器
 */
@Slf4j
@Component
public class MessageSender {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //@Scheduled(fixedRate = 2000) //间隔2s 通过StringRedisTemplate对象向redis消息队列chat频道发布消息
    public void sendMessage(MessageInfo messageInfo){
        String msg = JSON.toJSONString(messageInfo);
        log.info("转发消息 : {}", msg);
        stringRedisTemplate.convertAndSend("chat",msg);
    }
}
