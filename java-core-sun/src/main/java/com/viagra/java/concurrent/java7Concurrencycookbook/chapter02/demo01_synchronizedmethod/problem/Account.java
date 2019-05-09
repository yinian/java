package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.problem;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:21 2019/5/8
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
	public void addAmount(double amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
		System.out.println("存款：" + balance);
	}


	// 取款
	public void subtractAmount(double amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp -= amount;
		balance = tmp;

		//System.out.println("取款：" + balance);
	}
}
