package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo05_sleep;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description: Java并发API有另外一种方法使线程放弃CPU.是yield()方法,
 * 指示JVM线程对象可以放弃CPU给其它任务.
 * JVM不保证它一定会遵从(comply)这个请求.通常,它只是用来debug.
 * @Date: Created in 11:11 2019/5/5
 * @Modified By:
 */
public class FileClock implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s\n", new Date());
			try {
				// 睡眠1秒
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.out.printf("The FileClock has been interrupted");
			}

		}
	}
}
