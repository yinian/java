package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo12_threadfactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:04 2019/5/5
 * @Modified By:
 */
public class Task implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
