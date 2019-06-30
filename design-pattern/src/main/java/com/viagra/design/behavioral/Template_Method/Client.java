package com.viagra.design.behavioral.Template_Method;

/**
 * @Auther: viagra
 * @Date: 2019/6/30 18:53
 * @Description:
 */
public class Client {

    public static void main(final String[] args)
    {
        final AbstractClass c1 = new ConcreteClass1();
        final AbstractClass c2 = new ConcreteClass2();
        c1.templateMethod();
        c2.templateMethod();
    }
}
