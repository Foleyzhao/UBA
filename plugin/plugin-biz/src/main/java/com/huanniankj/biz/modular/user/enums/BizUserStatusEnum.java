package com.huanniankj.biz.modular.user.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 人员状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizUserStatusEnum {

    /**
     * 正常
     */
    ENABLE("ENABLE"),

    /**
     * 停用
     */
    DISABLED("DISABLED");

    private final String value;

    BizUserStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的人员状态：{}", value);
        }
    }
}
