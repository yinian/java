package com.viagra.java.concurrent.future.demo2;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:51 2019/4/20
 * @Modified By:
 */
public class Discount {

	public enum Code{
		NONE(0),SILVER(5),GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;

		Code(int percentage) {
			this.percentage = percentage;
		}
	}

	public static PriceRecord applyDiscount(BigDecimal price) {
		// values() 是枚举类型转换为数组，因为枚举类型没有下标
		Code discountCode = Code.values()[Randomizer.random(Code.values().length)];
		BigDecimal finalPrice = Discount.apply(price,discountCode);
		return new PriceRecord(discountCode,finalPrice);
	}



	// 东西的售价
	private static BigDecimal apply(BigDecimal price, Code code) {
		SlowNetwork.delay(1);
		return BigDecimal.valueOf(price.doubleValue() * (100 - code.percentage)/100);
	}




	}
