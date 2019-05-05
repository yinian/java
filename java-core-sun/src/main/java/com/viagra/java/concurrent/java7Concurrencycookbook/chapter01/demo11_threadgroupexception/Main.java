package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo11_threadgroupexception;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:43 2019/5/5
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		//创建线程组
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		Task task = new Task();
		// 可以增大创建的线程数量,效果会更明显
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
/*	但线程中抛出一个未捕获的异常,JVM会寻找3种可能的方式处理这个异常.
	首先,它寻找线程的未捕获的异常处理器,正如我们之前在"处理线程中不受控制的异常"章节所说明的.
	(setUncaughtExceptionHandler(....)).如果这个处理器不存在,JVM会查找这个线程的线程组的未捕获异常处理器,
	正如这节我们之前学习到的.
	如果这个方法也不存在,JVM会查找默认的未捕获异常处理器,如我们在"处理线程中不受控制的异常"章节所说明的.
	如果一个方法都不存在,JVM会在屏幕上输出堆栈信息,然后退出程序.*/
}
