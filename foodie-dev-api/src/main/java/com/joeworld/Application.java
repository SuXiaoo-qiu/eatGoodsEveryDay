package com.joeworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//扫码mapper
@MapperScan(basePackages = "com.joeworld.mapper")
//扫描所有包以及组件包
@ComponentScan(basePackages = {"com.joeworld", "org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
public class Application {
    public static void main(String[] args) {
         SpringApplication.run(Application.class,args);
    }
}
