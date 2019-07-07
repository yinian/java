package com.viagra.multi_realm;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Auther: viagra
 * @Date: 2019/7/6 10:06
 * @Description: 1.继承UsernamePasswordToken添加loginType属性
 */
public class UserToken extends UsernamePasswordToken {

    private String loginType;

    public UserToken(final String username, final String password,
                     final String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
/**
 这个类主要是模拟学生，老师实现多Realm登录，
 目前就建表了，只是弄几个用户实现学生，老师的角色


 */