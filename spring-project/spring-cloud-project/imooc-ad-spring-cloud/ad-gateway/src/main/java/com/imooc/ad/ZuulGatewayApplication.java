package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:48 2019/4/20
 * @Modified By:
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class,args);
	}
}
