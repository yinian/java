package com.viagra.design.behavioral.Template_Method;

/**
 * @Auther: viagra
 * @Date: 2019/6/30 18:52
 * @Description:
 */
public class ConcreteClass2 extends AbstractClass
{
    @Override
    protected void doAnyting()
    {
        System.out.println("do class2 anything");
    }

    @Override
    protected void doSomething()
    {
        System.out.println("do class2 something");
    }
}
