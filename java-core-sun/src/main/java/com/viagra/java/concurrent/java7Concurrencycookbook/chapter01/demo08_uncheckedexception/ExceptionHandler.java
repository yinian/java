package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo08_uncheckedexception;

/**
 * @Author: HASEE
 * @Description: 处理线程中抛出的未检查异常
 * @Date: Created in 14:18 2019/5/5
 * @Modified By:
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {

		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}

}
