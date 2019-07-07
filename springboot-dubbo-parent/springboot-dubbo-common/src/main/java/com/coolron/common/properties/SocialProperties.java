package com.coolron.common.properties;

import lombok.Data;

/**
 * @Auther: xf
 * @Date: 2018/10/31 15:16
 * @Description:
 */
@Data
public class SocialProperties {

    private WeixinProperties weixin = new WeixinProperties();
    private String[] clients = {};  // 默认空数组

}

