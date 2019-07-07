package com.viagra.dao;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: viagra
 * @Date: 2019/6/23 09:38
 * @Description: 登录相关dao
 */

public interface LoginDao {

    /**
     * 根据用户名和密码查询对应的用户
     */
    JSONObject getUser(@Param("username") String username, @Param("password") String password);
    /**
     * 根据用户名查询对应的用户
     */
    JSONObject getUserByUserName(@Param("username") String username);
}
