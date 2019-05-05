package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo12_threadfactory;

import sun.security.provider.Sun;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:04 2019/5/5
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {

		MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");

		Task task = new Task();
		Thread thread;
		System.out.printf("Starting theThreads\n");
		for (int i = 0; i < 5; i++) {
			thread = factory.newThread(task);
			thread.start();

		}

		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", factory.getStats());
	}

	/*
	Starting theThreads
	Factory stats:
	Created thread 12 with name MyThreadFactory-Thread_0 on Sun May 05 16:18:57 CST 2019
	Created thread 13 with name MyThreadFactory-Thread_1 on Sun May 05 16:18:57 CST 2019
	Created thread 14 with name MyThreadFactory-Thread_2 on Sun May 05 16:18:57 CST 2019
	Created thread 15 with name MyThreadFactory-Thread_3 on Sun May 05 16:18:57 CST 2019
	Created thread 16 with name MyThreadFactory-Thread_4 on Sun May 05 16:18:57 CST 2019
	*/

}
