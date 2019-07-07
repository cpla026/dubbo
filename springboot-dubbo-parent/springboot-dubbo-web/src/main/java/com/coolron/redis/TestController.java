package com.coolron.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xf
 * @Date: 2018/11/3 11:57
 * @Description:  消息发布测试
 */
@RestController
@RequestMapping("/redis/")
public class TestController {

    // Redis 消息发送器
    @Autowired
    private MessageSender messageSender;

    @RequestMapping(value = "/sendMsg/{sessionId}", method = RequestMethod.GET)
    public String sendRedisMsg(@PathVariable String sessionId) {
        MessageInfo messageInfo = new MessageInfo(sessionId,"转发请求");
        messageSender.sendMessage(messageInfo);
        return "The message of Redis sent successfully";
    }
}
