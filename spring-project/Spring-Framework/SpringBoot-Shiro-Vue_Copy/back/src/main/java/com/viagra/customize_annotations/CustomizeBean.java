package com.viagra.customize_annotations;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: viagra
 * @Date: 2019/7/4 08:08
 * @Description:
 */
public class CustomizeBean {

    /**
     * 启用注解拦截方式
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new ShiroAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    public SecurityManager securityManager(){
        return null;
    }
}
