package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.solution;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:51 2019/5/8
 * @Modified By:
 */
public class Account {

	// 余额
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// 存款
	public synchronized void addAmount(double amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		tmp += amount;
		balance = tmp;
	}

	// 取款
	public synchronized void subtractAmount(double amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;
	}
}
