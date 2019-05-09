package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.problem;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:21 2019/5/8
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {

		Account account = new Account();
		// 账户有1000元
		account.setBalance(1000);

		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);
		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

		companyThread.start();
		bankThread.start();

		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*

		Account : Initial Balance: 1000.000000
		Account : Final Balance: 101000.000000
	 */
}
