package com.viagra.java.concurrent.CountDownLatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:54 2019/5/7
 * @Modified By:
 */
public class Service {

	private CountDownLatch latch;

	public Service(CountDownLatch latch) {
		this.latch = latch;
	}

	public void exec() {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			System.out.println("Time: " + df.format(new Date()) + ", " + Thread.currentThread().getName() + " execute task. ");
			sleep(2);
			System.out.println("Time: " + df.format(new Date()) + ", " + Thread.currentThread().getName() + " finished task. ");
		} finally {
			latch.countDown();
		}
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
