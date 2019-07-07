package com.viagra.controller;
import com.alibaba.fastjson.JSONObject;
import com.viagra.multi_realm.UserToken;
import com.viagra.service.LoginService;
import com.viagra.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: viagra
 * @Date: 2019/6/23 09:28
 * @Description: 登录相关Controller
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 登录
	 */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson,"username,password");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo(){
        return loginService.getInfo();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }
    // -------------------------多Realm模拟  Start------------,这里写死了
    // username: test password: 123456 -> student
    @RequestMapping("/studentLogin")
    public JSONObject studentLogin(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "password") String password,
                                   HttpServletRequest request, HttpServletResponse response){


        System.out.println("\n学生登录");
        String msg = null;

        // 设置永不过期
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        Subject subject = SecurityUtils.getSubject();
        JSONObject jsonObject = null;
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UserToken(username,password,"Student"));
            jsonObject = loginService.getUser(username, password);
        } catch (AuthenticationException e) {
            System.out.println("登陆失败: " + e.getMessage());
        }

        return jsonObject;
    }


    /**
     * @Author Adam
     * @Description  家长登陆
     * @Date 23:01 2018/9/1
     * @Param [request, map, school]
     * @return boolean
     */
    // // username: user password: 123456 -> parent
    @RequestMapping("/parentLogin")
    public JSONObject parentLogin(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        System.out.println("\n家长登陆");
        JSONObject parent = null;
        //设置永不过期
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        Subject subject = SecurityUtils.getSubject();
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UserToken(username, password, "Parent"));
            parent = loginService.getUser(username, password);
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
        }
        return parent;

    }

    /**
     * @Author Adam
     * @Description  老师登陆
     * @Date 23:01 2018/9/1
     * @Param [request, map, school]
     * @return boolean
     */
    // username: admin password: 123456 -> teacher
    @RequestMapping("/teacherLogin")
    public JSONObject teacherLogin(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        System.out.println("\n老师登陆");
        JSONObject teacher = null;
        //设置永不过期
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);
        Subject subject = SecurityUtils.getSubject();
        try {
            // 调用安全认证框架的登录方法
            subject.login(new UserToken(username, password, "Teacher"));
            teacher = loginService.getUser(username, password);
        } catch (AuthenticationException ex) {
            System.out.println("登陆失败: " + ex.getMessage());
        }
        return teacher;

    }




    // -------------------------多Realm模拟  End------------










}
