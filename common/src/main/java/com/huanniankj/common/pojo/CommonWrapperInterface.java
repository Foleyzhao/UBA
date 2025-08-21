package com.huanniankj.common.pojo;

import cn.hutool.json.JSONObject;

/**
 * 通用包装接口
 *
 * @author happynewyear
 */
public interface CommonWrapperInterface<T> {

    /**
     * 执行包装
     */
    JSONObject doWrap(T wrapperObject);
}
