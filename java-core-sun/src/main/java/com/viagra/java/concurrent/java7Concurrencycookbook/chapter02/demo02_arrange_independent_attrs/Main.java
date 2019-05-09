package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo02_arrange_independent_attrs;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:21 2019/5/8
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {

		// 1个电影院两个卖票窗口，两场不同的电影
		Cinema cinema = new Cinema();

		TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
		Thread thread1 = new Thread(ticketOffice1, "TicketOffice1");

		TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
		Thread thread2 = new Thread(ticketOffice2, "TicketOffice2");

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Room 1 Vacancies: %d\n", cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n", cinema.getVacanciesCinema2());


	}
}
