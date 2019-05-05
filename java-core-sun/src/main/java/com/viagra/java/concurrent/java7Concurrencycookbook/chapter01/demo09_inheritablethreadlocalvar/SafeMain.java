package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo09_inheritablethreadlocalvar;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:45 2019/5/5
 * @Modified By:
 */
public class SafeMain {

	public static void main(String[] args) {
		SafeTask task = new SafeTask();

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(task);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			thread.start();
		}

	}

	/*
	三个线程对象拥有自己的startDate属性值.
		Starting Thread: 12 : Sun May 05 15:00:41 CST 2019
		Starting Thread: 13 : Sun May 05 15:00:43 CST 2019
		Thread Finished: 13 : Sun May 05 15:00:43 CST 2019
		Starting Thread: 14 : Sun May 05 15:00:45 CST 2019
		Thread Finished: 14 : Sun May 05 15:00:45 CST 2019
		Thread Finished: 12 : Sun May 05 15:00:41 CST 2019
		*/
}
