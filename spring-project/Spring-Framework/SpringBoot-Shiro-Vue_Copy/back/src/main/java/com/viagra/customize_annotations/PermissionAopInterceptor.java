package com.viagra.customize_annotations;

import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

/**
 * @Auther: viagra
 * @Date: 2019/7/4 08:01
 * @Description: 自定义注解的AOP拦截器
 */
public class PermissionAopInterceptor extends AopAllianceAnnotationsAuthorizingMethodInterceptor {
    public PermissionAopInterceptor() {
        super();
        // 添加自定义的注解拦截器
        this.methodInterceptors.add(new PermissionMethodInterceptor(new SpringAnnotationResolver()));
    }

}
