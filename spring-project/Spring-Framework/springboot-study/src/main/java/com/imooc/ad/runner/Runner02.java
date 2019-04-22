package com.imooc.ad.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:48 2019/4/21
 * @Modified By:
 */
//@Order(value = 2)
@Component
public class Runner02 implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		System.out.println("Running Runner02");
	}
}