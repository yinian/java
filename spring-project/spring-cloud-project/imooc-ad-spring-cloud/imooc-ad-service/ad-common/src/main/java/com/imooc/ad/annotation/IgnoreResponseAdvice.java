package com.imooc.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: HASEE
 * @Description: 自定义注解，在类或者方法上加；不会在CommonResponseDataAdvice类中被拦截
 * @Date: Created in 16:08 2019/4/21
 * @Modified By:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface IgnoreResponseAdvice {
}
