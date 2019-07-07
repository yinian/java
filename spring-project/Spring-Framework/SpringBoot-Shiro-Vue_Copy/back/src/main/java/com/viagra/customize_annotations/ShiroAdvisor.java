package com.viagra.customize_annotations;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @Auther: viagra
 * @Date: 2019/7/4 08:03
 * @Description: 自定义的Shiro注解拦截器
 */
public class ShiroAdvisor extends AuthorizationAttributeSourceAdvisor {
    /**
     * Create a new AuthorizationAttributeSourceAdvisor.
     */
    public ShiroAdvisor() {
        // 这里可以添加多个
        setAdvice(new PermissionAopInterceptor());
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        Method m = method;
        if (targetClass != null) {

            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return this.isFrameAnnotation(m);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return super.matches(method, targetClass);
    }

    private boolean isFrameAnnotation(Method method) {
        return null != AnnotationUtils.findAnnotation(method, Permissions.class);
    }
}
