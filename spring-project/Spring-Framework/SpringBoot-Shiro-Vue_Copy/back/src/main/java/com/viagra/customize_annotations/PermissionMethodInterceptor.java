package com.viagra.customize_annotations;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

/**
 * @Auther: viagra
 * @Date: 2019/7/4 07:57
 * @Description: 自定义注解的方法拦截器
 */
public class PermissionMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {

    public PermissionMethodInterceptor() {
        super(new PermissionHandler());
    }

    public PermissionMethodInterceptor(AnnotationResolver resolver) {
        super(new PermissionHandler(), resolver);
    }


    @Override
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
        // 验证权限
        try {
            ((PermissionHandler) getHandler()).assertAuthorized(getAnnotation(mi));
        } catch (AuthorizationException ae) {
            // Annotation handler doesn't know why it was called, so add the information here if possible.
            // Don't wrap the exception here since we don't want to mask the specific exception, such as
            // UnauthenticatedException etc.
            if (ae.getCause() == null) {
                ae.initCause(new AuthorizationException("Not authorized to invoke method: " + mi.getMethod()));
            }
            throw ae;
        }
    }
}
