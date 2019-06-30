package com.viagra.design.behavioral.Template_Method;

/**
 * @Auther: viagra
 * @Date: 2019/6/30 18:51
 * @Description:
 */
public abstract class AbstractClass {

    protected abstract void doAnyting();

    protected abstract void doSomething();

    public void templateMethod()
    {
        doAnyting();
        doSomething();
    }
}
