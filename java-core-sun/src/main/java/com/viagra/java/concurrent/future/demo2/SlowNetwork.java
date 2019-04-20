package com.viagra.java.concurrent.future.demo2;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:03 2019/4/20
 * @Modified By:
 */
public class SlowNetwork {

	public static void delay(int seconds) {

		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	}
