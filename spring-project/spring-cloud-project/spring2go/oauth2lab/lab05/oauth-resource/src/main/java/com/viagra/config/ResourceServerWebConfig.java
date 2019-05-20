package com.viagra.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
@EnableWebMvc 只能添加到一个@Configuration配置类上，
用于导入Spring Web MVC configuration
可以有多个@Configuration类来实现WebMvcConfigurer，以定制提供的配置。
WebMvcConfigurer 没有暴露高级设置，如果需要高级设置 需要
删除@EnableWebMvc并继承WebMvcConfigurationSupport

 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.viagra.web.controller" })
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    //
}
