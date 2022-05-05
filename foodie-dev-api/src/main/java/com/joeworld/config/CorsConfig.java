package com.joeworld.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    public CorsConfig(){

    }
    @Bean
    public CorsFilter crsFilter(){
        //添加cors 配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        //是否发送cookie信息
        config.setAllowCredentials(true);
        //设置允许的请求方式
        config.addAllowedMethod("*");
        //设置允许的header
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);
        return  new CorsFilter(corsSource);
    }
}
