package com.viagra.multi_realm;

import com.alibaba.fastjson.JSONObject;
import com.viagra.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: viagra
 * @Date: 2019/7/6 13:57
 * @Description:
 */
public class ParentRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * @Author Adam
     * @Description  主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * @Date 14:06 2018/9/28
     * @Param [token]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        // 获取用户的输入的账号
        String username = (String) token.getPrincipal();
        Object credentials = token.getPrincipal();
        System.out.println("credentials="+credentials);
        //通过username从数据库中查找对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        JSONObject jsonObject = loginService.getUserByUserName(username);
        System.out.printf("-------------->>parent="+jsonObject);
        if (jsonObject == null){
            return null;

        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                jsonObject.getString("username"),
                jsonObject.getString("password"),
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
        return (AuthenticationInfo) authenticationInfo;
    }
}
