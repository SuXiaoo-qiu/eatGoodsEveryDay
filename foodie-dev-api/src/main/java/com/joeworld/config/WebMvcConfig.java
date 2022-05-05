package com.joeworld.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    String filelocation="F:/file/";
    // 实现静态资源的映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/") //映射 swagger2
                .addResourceLocations("file:/" + filelocation); //映射本地静态文件
    }

    /**
     * 使用spring 自带的请求别的接口的方式
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate (RestTemplateBuilder builder){
        return builder.build();
    }
}
