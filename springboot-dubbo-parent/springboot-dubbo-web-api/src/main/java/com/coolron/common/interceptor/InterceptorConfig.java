package com.coolron.common.interceptor;

import com.coolron.common.validate.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Auther: xf
 * @Date: 2018/11/19 21:09
 * @Description:
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    /*
     * 拦截器配置
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
         //registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**").excludePathPatterns("/login");
         //registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");

        // 权限校验拦截器配置
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");

        // 父类的配置
        super.addInterceptors(registry);
    }
}
