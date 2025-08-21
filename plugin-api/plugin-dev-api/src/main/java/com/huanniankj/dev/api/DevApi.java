package com.huanniankj.dev.api;

import cn.hutool.json.JSONObject;

/**
 * 开发工具模块综合API
 *
 * @author happynewyear
 */
public interface DevApi {

    /**
     * 获得dev模块运维数据
     */
    JSONObject getDevOpCount();

    /**
     * 获得dev工具数量（短信、邮件、文件、消息）
     */
    JSONObject getToolDataCount();
}
