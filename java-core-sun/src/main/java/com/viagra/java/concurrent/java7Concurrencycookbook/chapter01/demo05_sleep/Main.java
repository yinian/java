package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo05_sleep;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:12 2019/5/5
 * @Modified By:
 */
public class Main {


	public static void main(String[] args) {
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);

		thread.start();

		try {
			//等待5秒
			TimeUnit.SECONDS.sleep(5);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 中断线程
		thread.interrupt();
	}
}
