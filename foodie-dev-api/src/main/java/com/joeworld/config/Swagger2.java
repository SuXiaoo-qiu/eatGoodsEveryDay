package com.joeworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

//配置swagger2 核心配置docket
    @Bean
    public Docket createRestApi() {
        return  new Docket(DocumentationType.SWAGGER_2) //指定api类型为swagger2
                .apiInfo(apiInfo())             //定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.joeworld.controller"))  //执行controller
                .paths(PathSelectors.any())  //所有controller
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("天天吃货电商平台api")  //文档标题
                .contact(new Contact("joeworld","www.jow.com","87381442@qq.com")) // 联系人信息
                .description("天天吃货api文档")               //详细信息
                .version("1.0.1")                           //版本
                .termsOfServiceUrl("www.jow.com")           //网站地址
                .build();

    }
}
