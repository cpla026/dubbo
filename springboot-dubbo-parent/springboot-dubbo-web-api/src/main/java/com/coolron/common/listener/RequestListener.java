package com.coolron.common.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @Auther: xf
 * @Date: 2018/11/19 21:42
 * @Description:  监听器
 */
@Slf4j
//@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("监听器销毁>>>>>");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("监听器初始化>>>>>");
    }
}
