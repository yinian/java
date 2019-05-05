package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo08_uncheckedexception;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:18 2019/5/5
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		thread.start();
	}
	/**
	 An exception has been captured
	 Thread: 9
	 Exception: java.lang.NumberFormatException: For input string: "TTT"
	 Stack Trace:
	 java.lang.NumberFormatException: For input string: "TTT"
	 at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	 at java.lang.Integer.parseInt(Integer.java:492)
	 at java.lang.Integer.parseInt(Integer.java:527)
	 at chapter01.threadmanager.demo08_uncheckedexception.Task.run(Task.java:8)
	 at java.lang.Thread.run(Thread.java:722)
	 Thread status: RUNNABLE
	 Thread has finished
	 */


}
