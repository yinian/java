package com.viagra.java.concurrent.future.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 15:50 2019/4/20
 * @Modified By:
 */
@Data
@AllArgsConstructor
public class PriceRecord {
	private Discount.Code code;
	private BigDecimal price;
}
