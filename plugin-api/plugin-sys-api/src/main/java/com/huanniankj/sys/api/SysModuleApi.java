package com.huanniankj.sys.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 模块API
 *
 * @author happynewyear
 */
public interface SysModuleApi {

    /**
     * 获取所有模块
     */
    List<JSONObject> moduleSelector();
}
