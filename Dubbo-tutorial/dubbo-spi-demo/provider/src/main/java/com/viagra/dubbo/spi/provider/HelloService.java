package com.viagra.dubbo.spi.provider;

import com.viagra.dubbo.spi.api.IHelloService;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 16:17
 * @Description:
 */
public class HelloService implements IHelloService {
    @Override
    public String hello(String name) {
        System.out.println("hello" + name);
        return "hello " + name;
    }
}
