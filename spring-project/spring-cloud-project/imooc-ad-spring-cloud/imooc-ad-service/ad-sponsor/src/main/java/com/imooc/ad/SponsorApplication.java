package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 6:39 2019/4/22
 * @Modified By:
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class SponsorApplication {

	public static void main(String[] args) {

		SpringApplication.run(SponsorApplication.class, args);
	}
}
