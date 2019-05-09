package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo01_synchronizedmethod.solution;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 21:51 2019/5/8
 * @Modified By:
 */
public class Main {
	public static void main(String[] args) {
		Account account = new Account();
		// ³õÊ¼Óà¶î
		account.setBalance(1000);

		// ´´½¨CompanyÏß³Ì
		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		// ´´½¨BankÏß³Ì
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

	/**
	 Account : Initial Balance: 1000.000000
	 Account : Final Balance: 1000.000000
	 */
}
