package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo10_threadgroup;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:13 2019/5/5
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		//创建ThreadGroup
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();

		SearchTask searchTask = new SearchTask(result);
		for (int i = 0; i < 5; i++) {
			//所属于线程组
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Grouper\n");
		// list()：打印线程的信息,用来debug的
		threadGroup.list();

		// 存活的线程对象
		Thread[] threads = new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}
		// 等待线程结束
		waitFinish(threadGroup);
		//中断所有的线程
		threadGroup.interrupt();
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		// 5个线程只要一个结束，那么就会结束循环
		while (threadGroup.activeCount() > 4) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
/*
	Thread Thread-0: Start
	Thread Thread-0: 84
	Thread Thread-1: Start
	Thread Thread-1: 12
	Thread Thread-2: Start
	Thread Thread-2: 1
	Thread Thread-3: Start
	Thread Thread-2: End
	Thread Thread-3: 29
	Thread Thread-4: Start
	Thread Thread-4: 20
	Number of Threads: 4
	Information about the Thread Grouper
	java.lang.ThreadGroup[name=Searcher,maxpri=10]
	Thread[Thread-0,5,Searcher]
	Thread[Thread-1,5,Searcher]
	Thread[Thread-3,5,Searcher]
	Thread[Thread-4,5,Searcher]
	Thread Thread-0: TIMED_WAITING
	Thread Thread-1: TIMED_WAITING
	Thread Thread-3: TIMED_WAITING
	Thread Thread-4: TIMED_WAITING
	Thread Thread-0: Interrupted
	Thread Thread-1: Interrupted
	Thread Thread-3: Interrupted
	Thread Thread-4: Interrupted
	*/


}
