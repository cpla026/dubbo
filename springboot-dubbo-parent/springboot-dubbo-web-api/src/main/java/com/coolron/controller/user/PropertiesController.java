package com.coolron.controller.user;

import com.coolron.common.properties.CustomProperties;
import com.coolron.common.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: xf
 * @Date: 2018/10/31 15:06
 * @Description: 测试自定义属性
 */
@Slf4j
@RestController
public class PropertiesController {

    @Value("${custom.properties.social.weixin.app-id}")
    private String appId;

    @Value("${custom.properties.social.clients[0]}")
    private String clientId;

    // 注入配置文件读取器
    @Autowired
    private CustomProperties customProperties;

    @GetMapping(value = "getProperties")
    public ApiResult test(){
        log.info("custom.properties.social.weixin.app-id: ,{}", appId);
        log.info("custom.properties.social.clients[0].clientId: ,{}", clientId);
        return ApiResult.ok(appId + ">>>>>>>>>>" + clientId);
    }

    @GetMapping(value = "getBeanPro")
    public ApiResult beanPro(){
        log.info("app-id: ,{}", customProperties.getSocial().getWeixin().getAppId());
        log.info("clients[0].clientId: ,{}", customProperties.getSocial().getClients()[0]);
        log.info("clients[1].clientId: ,{}", customProperties.getSocial().getClients()[1]);
        return ApiResult.ok(appId + ">>>>>>>>>>" + clientId);
    }
}
