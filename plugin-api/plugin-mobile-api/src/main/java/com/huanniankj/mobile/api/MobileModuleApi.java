package com.huanniankj.mobile.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 移动端菜单API
 *
 * @author happynewyear
 */
public interface MobileModuleApi {

    /**
     * 获取移动端模块选择器
     */
    List<JSONObject> mobileModuleSelector();
}
