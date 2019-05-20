package com.viagra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*
在SpringBoot2.0及Spring 5.0 WebMvcConfigurerAdapter已被废弃，目前找到解决方案就有两种
1 直接实现WebMvcConfigurer （官方推荐）
WebMvcConfigurerAdapter -- 想通过一个URL Mapping然后不经过Controller处理直接跳转到页面上的需求
 */
@Configuration
public class UiWebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void configureDefaultServletHandling(
            final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();

    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {

        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("forward:/index");
        registry.addViewController("/oauthTemp");
        registry.addViewController("/index");


    }
}