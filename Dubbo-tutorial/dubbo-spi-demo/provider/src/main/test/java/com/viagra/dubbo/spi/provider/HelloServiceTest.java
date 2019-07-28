package com.viagra.dubbo.spi.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: viagra
 * @Date: 2019/7/28 16:17
 * @Description:
 */
public class HelloServiceTest {

    @Test
    public void start_provider_1() throws Exception {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"dubbo-provider.xml"});
        context.start();
        System.out.println("Dubbo Provider 1 started successfully...");
        System.in.read();

    }


    @Test
    public void start_provider_2() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-provider2.xml"});
        context.start();
        System.out.println("Dubbo Provider 2 started successfully...");
        System.in.read();
    }

}
