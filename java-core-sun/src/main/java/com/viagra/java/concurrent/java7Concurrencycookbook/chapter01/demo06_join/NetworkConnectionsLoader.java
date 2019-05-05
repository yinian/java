package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo06_join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:30 2019/5/5
 * @Modified By:
 */
public class NetworkConnectionsLoader implements Runnable {
	@Override
	public void run() {
		System.out.printf("Begining network connections loading: %s\n", new Date());
		// 等待6秒
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Network connections loading has finished: %s\n", new Date());
	}
}
