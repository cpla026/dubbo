package com.coolron.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: xf
 * @Date: 2018/11/19 21:52
 * @Description:  swagger
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
// 选择不同的环境启用 swagger
@Profile({"dev","test"})
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com/coolron"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：.......")
                .termsOfServiceUrl("http://blog.com.coolron.com/")
                .contact("A罗恩和Java")
                .version("1.0")
                .build();
    }

}
