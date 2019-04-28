package com.viagra.java.concurrent.java7Concurrencycookbook.chapter01.demo04_controlinterruption;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 17:53 2019/4/22
 * @Modified By:
 */
public class Main {

	public static void main(String[] args) {
		FileSearch searcher = new FileSearch("E:\\", "index.jsp");
		Thread thread = new Thread(searcher);
		thread.start();

		// µÈ´ý1Ãë
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ÖÐ¶ÏÏß³Ì
		thread.interrupt();
	}
}
