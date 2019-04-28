package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo03_interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 17:37 2019/4/22
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread task = new PrimeGenerator();
		task.start();	// 运行

		// 等待5秒
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 中断PrimeGenerator线程, 但是并不会终止线程的执行
		task.interrupt();
		task.sleep(5000);
		System.out.println(task.getState());	// TERMINATED
		task.interrupt();	// 这里如果线程终止了,再次调用interrupt()是不会抛出异常的
	}
}
