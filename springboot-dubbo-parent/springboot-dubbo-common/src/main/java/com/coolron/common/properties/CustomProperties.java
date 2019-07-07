package com.coolron.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取器  读取整个系统配置
 * @ConfigurationProperties: 将本类中的属性和配置文件中相关配置进行绑定
 */
// 会读取 custom.properties 开头的配置项
@Data
// 加载指定的配置文件
@PropertySource("classpath:custom.properties")
@Component
@ConfigurationProperties(prefix = "custom.properties")
public class CustomProperties {

    // social 会读取到 此类中去
    private SocialProperties social = new SocialProperties();
}
