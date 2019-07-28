package com.alibaba;

import com.alibaba.spi.service.OrderService;

import java.util.ServiceLoader;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 10:15
 * @Description:
 */
public class TestSpi {

    public static void main(String[] args) {
        ServiceLoader<OrderService> orderServices =
                ServiceLoader.load(OrderService.class);
        for (OrderService orderService : orderServices){
            orderService.getOrderCountById(1);
        }
    }


}
