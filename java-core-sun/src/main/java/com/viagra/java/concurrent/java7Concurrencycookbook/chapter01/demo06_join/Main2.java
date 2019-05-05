package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo06_join;

import java.util.Date;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:27 2019/5/5
 * @Modified By:
 */
public class Main2 {


	public static void main(String[] args) {

		// 创建DataSourcesLoader线程
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader, "DataSourceThread");
		thread1.start();


		// 创建NetworkConnectionsLoader线程
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
		thread2.start();
		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
	}
	/**Begining data sources loading: Sun May 05 11:41:39 CST 2019
	 Main: Configuration has been loaded: Sun May 05 11:41:39 CST 2019
	 Begining network connections loading: Sun May 05 11:41:39 CST 2019
	 Data sources loading has finished: Sun May 05 11:41:43 CST 2019
	 Network connections loading has finished: Sun May 05 11:41:45 CST 2019
		Main线程先结束
	 */
}
