package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo09_inheritablethreadlocalvar;

import sun.security.provider.Sun;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:45 2019/5/5
 * @Modified By:
 */
public class SafeTask implements Runnable {

	// ThreadLocal
	private static ThreadLocal<Date> startDate = new ThreadLocal<Date>() {
		@Override
		protected Date initialValue() {
			return new Date();
		}
	};


	@Override
	public void run() {
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());

		try {
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());


	}
}
