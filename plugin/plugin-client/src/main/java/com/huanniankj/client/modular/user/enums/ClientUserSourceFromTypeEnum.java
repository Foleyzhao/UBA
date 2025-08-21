package com.huanniankj.client.modular.user.enums;

import lombok.Getter;

/**
 * 用户来源类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum ClientUserSourceFromTypeEnum {

    /**
     * 系统自建
     */
    SYSTEM_ADD("SYSTEM_ADD"),

    /**
     * 用户注册
     */
    SYSTEM_REGISTER("SYSTEM_REGISTER"),

    /**
     * 身份源
     */
    ID_SOURCE("ID_SOURCE");

    private final String value;

    ClientUserSourceFromTypeEnum(String value) {
        this.value = value;
    }
}
