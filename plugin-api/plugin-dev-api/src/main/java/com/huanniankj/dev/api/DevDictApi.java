package com.huanniankj.dev.api;

/**
 * 字典API
 *
 * @author happynewyear
 */
public interface DevDictApi {

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    String getDictLabel(String typeCode, String value);
}
