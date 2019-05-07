package com.viagra.java.concurrent.CountDownLatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 12:51 2019/5/7
 * @Modified By:
 */
public class Demo1 {

	// 模拟了100米赛跑，10名选手已经准备就绪，
	// 只等裁判一声令下。当所有人都到达终点时，比赛结束。
	public static void main(String[] args) throws InterruptedException {

		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch(1);
		//结束的倒数锁
		final CountDownLatch end = new CountDownLatch(10);

		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			final int NO = i + 1;

			Runnable run = new Runnable() {
				@Override
				public void run() {
					// 如果当前计数为0，则此方法立即返回。
					// 等待
					try {
						begin.await();
						Thread.sleep((long) (Math.random() * 10000));
						System.out.println("No." + NO + " arrived");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {

						//每个选手到达终点时,end-1
						end.countDown();
					}

				}
			};
			exec.submit(run);
		}
		System.out.println("Game Start");
		// begin减一，开始游戏
		begin.countDown();
		//等待end变为0,即所有选手到达终点
		end.await();
		System.out.println("Game Over");
		exec.shutdown();

	}

}
