package com.huanniankj.uba.api;

/**
 * 数据处理字典API
 *
 * @author happynewyear
 */
public interface DictApi {

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    String getDictLabel(String typeCode, String value);
}
