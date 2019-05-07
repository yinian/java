package com.viagra.java.concurrent.CountDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 14:13 2019/5/7
 * @Modified By:
 */
// 这次开始了数千个线程而不是5个线程，
// 很可能许多早期的线程在我们甚至在后者调用start（）之前已经完成了处理。
// 这可能会使尝试重现并发问题变得困难，
// 因为我们无法让所有线程并行运行
//	为了解决这个问题，让我们让CountdownLatch的工作方式与上一个例子不同。
// 在某些子线程完成之前，我们可以阻止每个子线程，
// 直到所有其他子线程都已启动，而不是阻塞父线程。
public class WaitingWorker implements Runnable {

	private List<String> outputScraper;
	private CountDownLatch readyThreadCounter;
	private CountDownLatch callingThreadBlocker;
	private CountDownLatch completedThreadCounter;

	public WaitingWorker(
			List<String> outputScraper,
			CountDownLatch readyThreadCounter,
			CountDownLatch callingThreadBlocker,
			CountDownLatch completedThreadCounter) {
		this.outputScraper = outputScraper;
		this.readyThreadCounter = readyThreadCounter;
		this.callingThreadBlocker = callingThreadBlocker;
		this.completedThreadCounter = completedThreadCounter;
	}


	@Override
	public void run() {

		readyThreadCounter.countDown();

		try {
			callingThreadBlocker.await();
			System.out.println("Doing some logic");
			outputScraper.add("Counted down");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			completedThreadCounter.countDown();
		}

	}
}
