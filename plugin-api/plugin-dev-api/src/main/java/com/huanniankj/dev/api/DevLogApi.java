package com.huanniankj.dev.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 日志API
 *
 * @author happynewyear
 */
public interface DevLogApi {

    /**
     * 记录登录日志
     */
    void executeLoginLog(String userName);

    /**
     * 记录登出日志
     */
    void executeLogoutLog(String userName);

    /**
     * 获取当前用户的访问日志列表
     */
    List<JSONObject> currentUserVisLogList();

    /**
     * 获取当前用户的操作日志列表
     */
    List<JSONObject> currentUserOpLogList();
}
