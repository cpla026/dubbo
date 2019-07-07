package com.coolron.service.impl.config;

import com.alibaba.dubbo.config.*;
import com.coolron.service.impl.user.UserServiceImpl;
import com.coolron.service.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: xf
 * @Date: 2018/12/01 23:58
 * @Description: dubbo api 配置类
 */
@Configuration
public class ServiceDubboConfig {
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("service-provider-api");
		return applicationConfig;
	}
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("101.200.42.170:2181");
		return registryConfig;
	}
	//<dubbo:protocol name="dubbo" port="20882"></dubbo:protocol>
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setPort(20880);
		return protocolConfig;
	}
	/**
	 *<dubbo:service interface="com.atguigu.gmall.service.UserService" 
		ref="userService" timeout="1000" version="1.0.0">
		<dubbo:method name="getUserAddressList" timeout="1000"></dubbo:method>
	</dubbo:service>
	 */
	@Bean
	public ServiceConfig<UserService> serviceConfig(){
		ServiceConfig<UserService> serviceConfig = new ServiceConfig<UserService>();
		serviceConfig.setApplication(applicationConfig());
		serviceConfig.setRegistry(registryConfig()); // 多个注册中心可以用setRegistries()
		serviceConfig.setProtocol(protocolConfig()); // 多个协议可以用setProtocols()
		serviceConfig.setInterface(UserService.class);
		serviceConfig.setRef(new UserServiceImpl());
		serviceConfig.setVersion("1.0.0");
		return serviceConfig;
	}
//	<dubbo:provider timeout="1000"></dubbo:provider>
	@Bean
	public ProviderConfig providerConfig() {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setTimeout(5000);
		return providerConfig;
	}
//	<dubbo:monitor address="127.0.0.1:7070"></dubbo:monitor>
//	@Bean
//	public MonitorConfig monitorConfig() {
//		MonitorConfig monitorConfig = new MonitorConfig();
//		monitorConfig.setAddress("127.0.0.1:7070");
//		monitorConfig.setProtocol("registry");
//		return monitorConfig;
//	}
}
