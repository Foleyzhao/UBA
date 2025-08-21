package com.huanniankj.dev.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 轮播图API
 *
 * @author happynewyear
 */
public interface DevSlideshowApi {

    /**
     * 通过位置获得轮播图列表
     */
    List<JSONObject> getListByPlace(String place);
}
