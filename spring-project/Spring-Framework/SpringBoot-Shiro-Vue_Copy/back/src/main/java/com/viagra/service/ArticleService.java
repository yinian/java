package com.viagra.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: viagra
 * @Date: 2019/6/26 07:16
 * @Description:
 */
public interface ArticleService {

    /**
     * 新增文章
     */
    JSONObject addArticle(JSONObject jsonObject);

    /**
     * 文章列表
     */
    JSONObject listArticle(JSONObject jsonObject);

    /**
     * 更新文章
     */
    JSONObject updateArticle(JSONObject jsonObject);
}
