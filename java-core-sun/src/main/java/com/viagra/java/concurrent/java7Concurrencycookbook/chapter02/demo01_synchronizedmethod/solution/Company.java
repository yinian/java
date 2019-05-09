package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.solution;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:51 2019/5/8
 * @Modified By:
 */
public class Company implements Runnable {

	private Account account;

	public Company(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.addAmount(1000);
		}
	}

}
