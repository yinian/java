package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo06_join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:27 2019/5/5
 * @Modified By:
 */
public class DataSourcesLoader implements Runnable {

	@Override
	public void run() {

		System.out.printf("Begining data sources loading: %s\n",new Date());
		try {
			// 等待4秒
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Data sources loading has finished: %s\n",new Date());
	}
}
