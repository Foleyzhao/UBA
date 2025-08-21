package com.huanniankj.uba.modular.user.enums;

import lombok.Getter;

/**
 * 运营用户来源枚举
 *
 * @author happynewyear
 */
@Getter
public enum UserSourceEnum {

    /**
     * API同步
     */
    API_SYNC("API_SYNC"),

    /**
     * API导入
     */
    API_IMPORT("API_IMPORT"),

    /**
     * 手动新增
     */
    MANUAL_ADD("MANUAL_ADD");


    private final String value;

    UserSourceEnum(String value) {
        this.value = value;
    }

}
