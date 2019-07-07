package com.viagra.service;
import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: viagra
 * @Date: 2019/6/25 07:17
 * @Description:
 */
public interface PermissionService {

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    JSONObject getUserPermission(String username);
}
