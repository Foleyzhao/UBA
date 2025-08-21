package com.huanniankj.common.enums;

import lombok.Getter;

/**
 * 通用性别枚举
 *
 * @author happynewyear
 */
@Getter
public enum CommonGenderEnum {

    /**
     * 未知
     */
    UNKNOWN("未知"),

    /**
     * 男
     */
    MAN("男"),

    /**
     * 女
     */
    WOMAN("女");

    private final String value;

    CommonGenderEnum(String value) {
        this.value = value.toLowerCase();
    }
}
