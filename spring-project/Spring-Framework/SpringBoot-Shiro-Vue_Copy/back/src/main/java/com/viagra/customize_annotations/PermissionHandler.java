package com.viagra.customize_annotations;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;

/**
 * @Auther: viagra
 * @Date: 2019/7/4 07:53
 * @Description: 自定义权限处理器
 */
public class PermissionHandler extends AuthorizingAnnotationHandler {

    public PermissionHandler() {
        super(Permissions.class);
    }

    @Override
    public void assertAuthorized(Annotation a)
            throws AuthorizationException {

        if (a instanceof Permissions) {

            Permissions annotation = (Permissions) a;
            String[] perms = annotation.value();
            Subject subject = getSubject();
            if (perms.length == 1) {

                subject.checkPermission(perms[0]);
                return;
            }
            // 多个权限，有一个就通过
            boolean hasAtLeastOnePermission = false;
            for(String permission : perms){
                if (subject.isPermitted(permission)){
                    hasAtLeastOnePermission = true;
                    break;
                }
            }

            // cause the exception if none of the role match,
            // note that the exception message will be a bit misleading
            if (!hasAtLeastOnePermission) {

                subject.checkPermission(perms[0]);
            }
        }
    }
}
