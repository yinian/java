package com.viagra.springintro; /**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 23:19 2018/8/20
 * @Modified By:
 */

import java.util.Calendar;

import com.viagra.springintro.bean.Employee;
import com.viagra.springintro.bean.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        // 读取applicationContext.xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-config.xml"});
        Product product1 = context.getBean("product", Product.class);
        product1.setName("Excellent snake oil");
        System.out.println("product1: " + product1.getName());
        //根据applicationContext.xml中的配置找到对应类的配置并实例化,调用实例化后的实例，返回结果
        Product product2 = context.getBean("product", Product.class);
        System.out.println("product2: " + product2.getName());

        Product featuredProduct = context.getBean("featuredProduct", Product.class);
        System.out.println(featuredProduct.getName() + ", " + featuredProduct.getDescription()
                + ", " + featuredProduct.getPrice());


        Employee employee1 = context.getBean("employee1", Employee.class);
        System.out.println(employee1.getFirstName() + " " + employee1.getLastName());
        System.out.println(employee1.getHomeAddress());

        Employee employee2 = context.getBean("employee2", Employee.class);
        System.out.println(employee2.getFirstName() + " " + employee2.getLastName());
        System.out.println(employee2.getHomeAddress());

    }

}
