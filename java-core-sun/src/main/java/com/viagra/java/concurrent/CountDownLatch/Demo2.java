package com.viagra.java.concurrent.CountDownLatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 12:51 2019/5/7
 * @Modified By:
 */
public class Demo2 {

	// 让CountDownLatch等待Worker实例完成
	@Test
	public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion()
			throws InterruptedException {

		List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
		CountDownLatch countDownLatch = new CountDownLatch(5);
		List<Thread> workers = Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
				.limit(5)
				.collect(Collectors.toList());

		workers.forEach(Thread::start);
		countDownLatch.await();
		outputScraper.add("Latch released");

		assertThat(outputScraper, hasItems("Counted down",
				"Counted down",
				"Counted down",
				"Counted down",
				"Counted down",
				"Latch released"));

	}

	//让我们修改我们的测试，
	// 使其阻塞直到所有工人都已启动，解锁工人，然后阻止直到工人完成
	@Test
	public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime()
			throws InterruptedException {
		List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
		CountDownLatch readyThreadCounter = new CountDownLatch(5);
		CountDownLatch callingThreadBlocker = new CountDownLatch(1);
		CountDownLatch completedThreadCounter = new CountDownLatch(5);

		List<Thread> workers = Stream.generate(() -> new Thread(new WaitingWorker(
				outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter
		))).limit(5).collect(Collectors.toList());

		workers.forEach(Thread::start);
		readyThreadCounter.await();
		outputScraper.add("Workers ready");
		callingThreadBlocker.countDown();
		completedThreadCounter.await();
		outputScraper.add("Workers complete");
		assertThat(outputScraper, hasItems(
				"Workers ready",
				"Counted down",
				"Counted down",
				"Counted down",
				"Counted down",
				"Counted down",
				"Workers complete"
		));
	}

	/**
	 * 主线程先启动了五个线程，然后主线程进入等待状态，当
	 * 这五个线程都执行完任务之后主线程才结束了等待。上述代码中需要注意的是，
	 * 在执行任务的线程中，使用了try...finally结构，
	 * 该结构可以保证创建的线程发生异常时CountDownLatch.countDown()方法也会执行，
	 * 也就保证了主线程不会一直处于等待状态。
	 * 应用：1.CountDownLatch非常适合于对任务进行拆分，使其并行执行
	 * 2. 批量获取某些资源
	 *
	 * @throws InterruptedException
	 */
	@Test
	public void CountDownLatchExample() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(5);
		Service service = new Service(latch);
		Runnable task = () -> service.exec();
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(task);
			thread.start();
		}
		System.out.println("main thread await. ");
		latch.await();
		System.out.println("main thread finishes await. ");
	}


}
