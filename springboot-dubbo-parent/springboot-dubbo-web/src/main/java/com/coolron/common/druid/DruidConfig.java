package com.coolron.common.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @Auther: xf
 * @Date: 2018/11/26 22:10
 * @Description: druid 配置类
 * http://localhost:8080/druid/login.html
 */
@Configuration
public class DruidConfig {
    /**
     * druid本身就是为了扩展jdbc的功能，而dataSource对象就是jdbc的配置
     */
    @Bean
    // 引入application.properties文件中以spring.datasource开头的信息
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }
    /**
     * Druid 监控视图配置
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //  ip 白名单 没有配置则允许所有访问
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        // ip 黑名单 优先级大于白名单
        servletRegistrationBean.addInitParameter("deny","192.168.0.19");//设置ip黑名单，优先级高于白名单
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","admin");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable","true");
        return servletRegistrationBean;
    }
    /**
     * 监控拦截器
     */
    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
