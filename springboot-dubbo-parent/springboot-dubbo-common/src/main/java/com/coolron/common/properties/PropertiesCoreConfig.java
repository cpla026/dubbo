package com.coolron.common.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: xf
 * @Date: 2018/10/31 16:00
 * @Description:
 */
@Configuration
// 作用 : 使 CustomProperties 配置文件读取器生效
@EnableConfigurationProperties(CustomProperties.class)
public class PropertiesCoreConfig {

}
