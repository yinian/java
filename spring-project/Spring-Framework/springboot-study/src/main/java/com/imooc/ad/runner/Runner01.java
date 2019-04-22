package com.imooc.ad.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:48 2019/4/21
 * @Modified By:
 */
//@Order(value = 1)
@Component
public class Runner01 implements CommandLineRunner{
	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Running Runner01");
	}
}
