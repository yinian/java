package com.viagra.java.concurrent.future.demo2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:06 2019/4/20
 * @Modified By:
 */
public class Randomizer {

	public static int random(int size) {
		return ThreadLocalRandom.current().nextInt(size);
	}
}
