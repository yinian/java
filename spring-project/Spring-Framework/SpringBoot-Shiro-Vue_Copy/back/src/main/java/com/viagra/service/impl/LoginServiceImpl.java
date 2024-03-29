package com.viagra.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.viagra.dao.LoginDao;
import com.viagra.service.LoginService;
import com.viagra.service.PermissionService;
import com.viagra.util.CommonUtil;
import com.viagra.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Auther: viagra
 * @Date: 2019/6/23 09:30
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private PermissionService permissionService;



    @Override
    public JSONObject authLogin(JSONObject jsonObject) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            info.put("result", "success");
        } catch (AuthenticationException e) {
            info.put("result", "fail");
        }
        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject getUser(String username, String password) {
        return loginDao.getUser(username, password);
    }

    @Override
    public JSONObject getUserByUserName(String username) {
        return loginDao.getUserByUserName(username);
    }

    @Override
    public JSONObject getInfo() {
        // 从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject info = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        info.put("userPermission", userPermission);
        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject logout() {

        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {

        }
        return CommonUtil.successJson();
    }
}
