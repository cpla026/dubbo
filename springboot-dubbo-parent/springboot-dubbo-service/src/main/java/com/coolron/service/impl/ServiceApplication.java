package com.coolron.service.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: xf
 * @Date: 2018/11/29 21:55
 * @Description:
 */
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.coolron.dao.*")
public class ServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
