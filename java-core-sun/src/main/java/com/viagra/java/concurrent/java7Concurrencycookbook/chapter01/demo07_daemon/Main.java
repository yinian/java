package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo07_daemon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:47 2019/5/5
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		Deque<Event> deque = new ArrayDeque<>();
		// WriterTask
		WriterTask writer = new WriterTask(deque);
		for(int i=0;i<3;i++){
			Thread thread = new Thread(writer);
			thread.start();
		}

		// CleanerTask
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();
	}
}
