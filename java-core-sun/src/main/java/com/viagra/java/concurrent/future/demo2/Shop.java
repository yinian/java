package com.viagra.java.concurrent.future.demo2;

import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:01 2019/4/20
 * @Modified By:
 */
@Data
public class Shop {
	private final String name;

	public Future<BigDecimal> getPriceAsync() {

		CompletableFuture<BigDecimal> future = new CompletableFuture<>();
		new Thread(() -> {
			BigDecimal price = calculatePrice();
			future.complete(price);
		}).start();
		return future;
	}

	public BigDecimal getPrice() {
		return calculatePrice();
	}

	public BigDecimal calculatePrice() {
		SlowNetwork.delay(1);
		return BigDecimal.valueOf(Randomizer.random(1000));
	}
}
