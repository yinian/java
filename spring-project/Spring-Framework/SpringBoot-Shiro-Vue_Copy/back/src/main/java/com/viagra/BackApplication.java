package com.viagra;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @description: SpringBoot启动项
 */
@SpringBootApplication
@MapperScan("com.viagra.dao")
public class BackApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(BackApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //		// 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(BackApplication.class);
    }
}
