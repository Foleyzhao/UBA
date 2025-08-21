package com.huanniankj.uba.modular.user.enums;

import lombok.Getter;

/**
 * 运营用户标签来源枚举
 *
 * @author happynewyear
 */
@Getter
public enum UserTagSourceEnum {

    /**
     * 手动
     */
    MANUAL("SYSTEM_ADD"),

    /**
     * 自动
     */
    AUTO("SYSTEM_REGISTER");

    private final String value;

    UserTagSourceEnum(String value) {
        this.value = value;
    }

}
