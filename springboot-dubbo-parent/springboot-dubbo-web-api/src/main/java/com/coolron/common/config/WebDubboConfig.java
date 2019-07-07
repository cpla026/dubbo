package com.coolron.common.config;

import com.alibaba.dubbo.config.*;
import com.coolron.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebDubboConfig {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("service-consumer-api");
		return applicationConfig;
	}
	
	//<dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("101.200.42.170:2181");
		return registryConfig;
	}

	//	<dubbo:consumer check="false" timeout="5000"></dubbo:consumer>
	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig consumerConfig = new ConsumerConfig();
		//consumerConfig.setCheck(false);;
		consumerConfig.setTimeout(5000);
		return consumerConfig;
	}
	
	
	/**
	 *<dubbo:reference interface="com.web.gmall.service.UserService" 
		id="userService" timeout="5000" retries="3" version="*">
		<dubbo:method name="getUserAddressList" timeout="1000" retries="0"></dubbo:method>
	</dubbo:reference>
	 */
	@Bean
	public ReferenceConfig<UserService> referenceConfig(){
		// 引用远程服务
		ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<UserService>();
		referenceConfig.setApplication(applicationConfig());
		referenceConfig.setRegistry(registryConfig()); // 多个注册中心可以用setRegistries()
		referenceConfig.setInterface(UserService.class);
		referenceConfig.setVersion("1.0.0");

		// 方法级配置
		List<MethodConfig> methods = new ArrayList<MethodConfig>();
		MethodConfig method = new MethodConfig();
		method.setName("getAllUser");
		method.setTimeout(10000);
		method.setRetries(0);
		methods.add(method);

		referenceConfig.setMethods(methods);

		//配置每一个method的信息
//		MethodConfig methodConfig = new MethodConfig();
//		methodConfig.setName("getAllUser");
//		methodConfig.setTimeout(1000);
//		methodConfig.setRetries(-1);
//
//		//将method的设置关联到service配置中
//		List<MethodConfig> methods = new ArrayList<>();
//		methods.add(methodConfig);
//		serviceConfig.setMethods(methods);

		return referenceConfig;

	}

//	<dubbo:monitor address="127.0.0.1:7070"></dubbo:monitor>
	/*@Bean
	public MonitorConfig monitorConfig() {
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setAddress("127.0.0.1:7070");
		monitorConfig.setProtocol("registry");
		return monitorConfig;
	}*/

}
