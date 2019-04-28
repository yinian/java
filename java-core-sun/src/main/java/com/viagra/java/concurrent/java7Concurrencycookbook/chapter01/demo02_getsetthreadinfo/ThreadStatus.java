package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo02_getsetthreadinfo;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:57 2019/4/22
 * @Modified By:
 */
public class ThreadStatus extends Thread{
	public ThreadStatus() {
		super("ThreadStatus");
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getState() + "=>" + i);

		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadStatus t = new ThreadStatus();
		System.out.println(t.getState()); // NEW

		t.start(); //RUNNABLE
		t.join();

		System.out.println("end");
		System.out.println(t.getState()); // TERMINATED


	}
}
