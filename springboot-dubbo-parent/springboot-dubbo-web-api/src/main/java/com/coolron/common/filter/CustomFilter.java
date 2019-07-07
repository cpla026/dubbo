package com.coolron.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther: xf
 * @Date: 2018/11/19 17:30
 * @Description: 自定义过滤器
 *
 * Filter 只能获取request 和 response 并不知道请求是哪个 controller 处理的
 */
@Slf4j
// 多个过滤器，使用此注解指定执行顺序，越小越先执行
//@Order(1)
// 在 CustomFilterConfig 中配置  注释掉 @WebFilter
//@WebFilter(urlPatterns = "/*", filterName = "customFilter")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化>>>>>>>");
    }

    /**
     * 请求被拦截的时候进行调用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(">>>>>>>>>>>>过滤器拦截请求处理逻辑>>>>>>>>>>>>>");

        // 业务逻辑
        long startTime = System.currentTimeMillis();

        // 过滤器链，给下一个过滤器
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("请求时间：" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁>>>>>>>");
    }
}
