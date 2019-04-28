package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo01_createrunthread;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 16:13 2019/4/22
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		for (int i = 1; i<=10;i++){
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
