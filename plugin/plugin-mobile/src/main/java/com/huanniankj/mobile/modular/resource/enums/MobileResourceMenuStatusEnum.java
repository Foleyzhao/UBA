package com.huanniankj.mobile.modular.resource.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 移动端资菜单状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum MobileResourceMenuStatusEnum {

    /**
     * 可用
     */
    ENABLE("ENABLE"),

    /**
     * 不可用
     */
    DISABLED("DISABLED");

    private final String value;

    MobileResourceMenuStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的资源分类：{}", value);
        }
    }
}
