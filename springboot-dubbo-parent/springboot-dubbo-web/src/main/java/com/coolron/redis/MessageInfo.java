package com.coolron.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: xf
 * @Date: 2018/11/20 23:08
 * @Description:  存放消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo implements Serializable{
    private static final long serialVersionUID = -1011233794643668350L;
    private String sessionId;
    private String message;
}
