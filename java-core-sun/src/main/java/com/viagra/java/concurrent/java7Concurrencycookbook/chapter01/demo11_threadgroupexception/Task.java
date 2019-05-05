package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo11_threadgroupexception;

import java.util.Random;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:43 2019/5/5
 * @Modified By:
 */
public class Task implements Runnable {
	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			// 直到random.nextDouble() =0,这里会抛出异常
			result = 1000 / ((int) (random.nextDouble() * 1000));
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			// 检查是否已经中断
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}
}
