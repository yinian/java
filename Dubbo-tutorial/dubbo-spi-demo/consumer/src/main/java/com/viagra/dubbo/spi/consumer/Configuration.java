package com.viagra.dubbo.spi.consumer;

import org.springframework.context.annotation.ImportResource;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 16:48
 * @Description:
 */
@org.springframework.context.annotation.Configuration
@ImportResource(locations = {"classpath*:dubbo-consumer.xml"})
public class Configuration {
}
