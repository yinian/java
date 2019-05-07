package com.viagra.java.concurrent.CountDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 13:25 2019/5/7
 * @Modified By:
 */
public class Worker implements Runnable {
	private List<String> outputScraper;
	private CountDownLatch countDownLatch;

	public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
		this.outputScraper = outputScraper;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		// Do some work
		System.out.println("Doing some logic");
		outputScraper.add("Counted down");
		countDownLatch.countDown();
	}
}
