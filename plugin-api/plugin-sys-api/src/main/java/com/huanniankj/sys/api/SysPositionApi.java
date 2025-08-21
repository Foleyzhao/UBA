package com.huanniankj.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 职位API
 *
 * @author happynewyear
 */
public interface SysPositionApi {

    /**
     * 根据id获取名称
     */
    String getNameById(String positionId);

    /**
     * 获取职位选择器
     */
    Page<JSONObject> positionSelector(String orgId, String searchKey, Integer current, Integer size);
}
