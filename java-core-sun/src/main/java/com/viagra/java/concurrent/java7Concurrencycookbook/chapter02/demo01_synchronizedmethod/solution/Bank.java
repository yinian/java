package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.solution;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:51 2019/5/8
 * @Modified By:
 */
// 模拟银行或自动提款机从账户中取钱
public class Bank implements Runnable {
	// 操作影响的账户
	private Account account;

	public Bank(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.subtractAmount(1000);
		}
	}

}
