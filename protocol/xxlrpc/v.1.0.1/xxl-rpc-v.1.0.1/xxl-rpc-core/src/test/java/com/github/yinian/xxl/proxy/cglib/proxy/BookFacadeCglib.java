package com.github.yinian.xxl.proxy.cglib.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 使用cglib动态代理
 *
 * @author yinian
 */
public class BookFacadeCglib implements MethodInterceptor {

    private Object target;

    // 创建代理对象
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {

        System.out.println("事物开始");
        methodProxy.invokeSuper(o, objects);
        System.out.println("事物结束");
        return null;

    }
}