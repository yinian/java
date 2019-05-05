package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo08_uncheckedexception;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:18 2019/5/5
 * @Modified By:
 */
public class Task implements Runnable {
	@Override
	public void run() {
		// 抛出异常
		// 这里的异常由ExceptionHandler处理
		Integer.parseInt("TTT");

		// 这里不会被执行到
		System.out.println("=====run end=====");
	}
}
