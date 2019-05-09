package com.viagra.java.concurrent.java7Concurrencycookbook.chapter02.demo02_arrange_independent_attrs;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 22:21 2019/5/8
 * @Modified By:
 */
public class TicketOffice1 implements Runnable {

	private Cinema cinema;

	public TicketOffice1(Cinema cinema) {
		this.cinema = cinema;
	}

	@Override
	public void run() {

		// 既有对Cinema1的操作,也有对Ciname2的操作
		cinema.sellTickets1(3);
		cinema.sellTickets1(2);
		cinema.sellTickets2(2);
		cinema.returnTickets1(3);
		cinema.sellTickets1(5);
		cinema.sellTickets2(2);
		cinema.sellTickets2(2);
		cinema.sellTickets2(2);


	}
}
