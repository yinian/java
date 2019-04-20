package com.viagra.springintro; /**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 23:19 2018/8/20
 * @Modified By:
 */

import com.viagra.springintro.bean.Employee;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class MainBean {
    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("spring-config.xml"));
        Employee testBean = (Employee) beanFactory.getBean("employee1");
        System.out.println(testBean.getFirstName() + " " + testBean.getLastName());
        System.out.println(testBean.getHomeAddress());


    }

}
