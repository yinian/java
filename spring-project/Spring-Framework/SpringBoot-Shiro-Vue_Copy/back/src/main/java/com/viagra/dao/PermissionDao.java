package com.viagra.dao;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;
/**
 * @Auther: viagra
 * @Date: 2019/6/25 07:19
 * @Description:
 */
public interface PermissionDao {

    /**
     * 查询用户的角色 菜单 权限
     */
    JSONObject getUserPermission(String username);

    /**
     * 查询所有的菜单
     */
    Set<String> getAllMenu();

    /**
     * 查询所有的权限
     */
    Set<String> getAllPermission();
}
