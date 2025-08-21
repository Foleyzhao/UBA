package com.huanniankj.auth.api;

import cn.hutool.json.JSONObject;

/**
 * 认证鉴权API
 *
 * @author happynewyear
 */
public interface AuthApi {

    /**
     * 获取基础登录业务数据，b端在线用户，c端在线用户
     */
    JSONObject getUserSessionCount();

    /**
     * 获取三方用户总量
     */
    Long getThirdUserCount();
}
