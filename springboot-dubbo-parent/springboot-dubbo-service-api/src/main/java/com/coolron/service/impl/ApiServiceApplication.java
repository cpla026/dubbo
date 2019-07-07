package com.coolron.service.impl;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/12/02 0:01
 * @Description:
 */
@DubboComponentScan(basePackages = "com.coolron.service.impl")
@SpringBootApplication
@MapperScan("com.coolron.dao.*")
public class ApiServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiServiceApplication.class, args);
	}
}
