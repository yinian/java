package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo09_inheritablethreadlocalvar;

import sun.security.provider.Sun;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:45 2019/5/5
 * @Modified By:
 */
public class UnsafeMain {

	public static void main(String[] args) {
		UnsafeTask task = new UnsafeTask();

		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				// 睡眠2秒
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	每个线程有不同的开始时间,但是当他们结束,startDate属性有同样的值.
	Starting Thread: 12 : Sun May 05 15:10:01 CST 2019
	Starting Thread: 13 : Sun May 05 15:10:03 CST 2019
	Starting Thread: 14 : Sun May 05 15:10:05 CST 2019
	Thread Finished: 13 : Sun May 05 15:10:05 CST 2019
	Thread Finished: 12 : Sun May 05 15:10:05 CST 2019
	Thread Finished: 14 : Sun May 05 15:10:05 CST 2019
	*/
}
