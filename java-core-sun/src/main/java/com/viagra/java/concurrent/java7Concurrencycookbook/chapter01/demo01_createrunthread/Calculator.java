package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo01_createrunthread;

/**
 * @Author: HASEE
 * @Description: This class prints the multiplication table of a number
 * @Date: Created in 16:12 2019/4/22
 * @Modified By:
 */
public class Calculator implements Runnable {

	/**
	 * The number
	 */
	private int number;

	/**
	 * Constructor of the class
	 *
	 * @param number
	 *            : The number
	 */
	public Calculator(int number) {
		this.number = number;
	}

	/**
	 * Method that do the calculations
	 */
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, i * number);
		}
	}

}