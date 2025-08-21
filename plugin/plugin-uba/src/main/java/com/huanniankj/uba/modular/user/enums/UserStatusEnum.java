package com.huanniankj.uba.modular.user.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 运营用户状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum UserStatusEnum {

    /**
     * 正常
     */
    ENABLE("ENABLE"),

    /**
     * 停用
     */
    DISABLED("DISABLED");

    private final String value;

    UserStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的运营用户状态：{}", value);
        }
    }
}
