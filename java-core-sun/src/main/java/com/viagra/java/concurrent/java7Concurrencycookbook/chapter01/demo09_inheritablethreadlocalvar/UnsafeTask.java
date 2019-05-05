package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo09_inheritablethreadlocalvar;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:45 2019/5/5
 * @Modified By:
 */
public class UnsafeTask implements Runnable {

	// 所有的线程共享
	private Date startDate;

	@Override
	public void run() {
		startDate = new Date();
		System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
		try {
			// 休眠随机秒
			TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);
	}
}
