package com.alibaba.spi.service;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 09:58
 * @Description:
 */
public class CustomerOrderServiceImpl implements OrderService {
    @Override
    public int getOrderCountById(int id) {
        System.out.println("cutomer order count is 10");
        return 10;
    }
}
