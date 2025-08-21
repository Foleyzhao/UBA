package com.huanniankj.uba.modular.dict.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 数据处理字典分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum DictCategoryEnum {

    /**
     * 访问日志
     */
    ACCESS_LOG("ACCESS_LOG"),

    /**
     * 埋点
     */
    TRACKING("TRACKING");

    private final String value;

    DictCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = TRACKING.getValue().equals(value) || ACCESS_LOG.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的字典分类：{}", value);
        }
    }
}
