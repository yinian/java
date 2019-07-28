package com.alibaba.spi.service;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 10:00
 * @Description:
 */
public class AgencyOrderServiceImpl implements OrderService {
    @Override
    public int getOrderCountById(int id) {
        System.out.println("agency order count is 20");
        return 20;
    }
}
