package com.coolron.service.impl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Auther: xf
 * @Date: 2018/12/01 22:55
 * @Description:
 */
@ImportResource(locations = {"classpath:provider.xml"})
@SpringBootApplication
@MapperScan("com.coolron.dao.*")
public class XmlServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(XmlServiceApplication.class, args);
	}
}
