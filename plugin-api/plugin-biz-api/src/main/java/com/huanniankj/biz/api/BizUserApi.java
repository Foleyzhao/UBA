package com.huanniankj.biz.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 用户Api
 *
 * @author happynewyear
 */
public interface BizUserApi {

    /**
     * 获取用户选择器
     */
    Page<JSONObject> userSelector(String orgId, String searchKey);
}
