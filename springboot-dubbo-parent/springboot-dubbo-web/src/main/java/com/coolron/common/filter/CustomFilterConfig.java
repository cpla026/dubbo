package com.coolron.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: xf
 * @Date: 2018/11/19 17:58
 * @Description:  传统的项目配置 Filter 在 web.xml 中添加
 * 在Spring boot中，我们需要 FilterRegistrationBean 来完成配置
 */
//@Configuration
public class CustomFilterConfig {
    @Bean
    public FilterRegistrationBean customFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CustomFilter());
        registration.addUrlPatterns("/*");
        registration.setName("customFilter");
        registration.setOrder(1);
        return registration;
    }
}
