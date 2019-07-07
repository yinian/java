package com.viagra.service;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @Auther: viagra
 * @Date: 2019/6/23 09:30
 * @Description:
 */
public interface LoginService {
    /**
     * 登录表单提交
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     */
    JSONObject getUser(String username, String password);

    /**
     * 根据用户名查询对应的用户
     *
     * @param username 用户名
     */
    JSONObject getUserByUserName(String username);


    /**
     * 查询当前登录用户的权限等信息
     */
    JSONObject getInfo();

    /**
     * 退出登录
     */
    JSONObject logout();

}
