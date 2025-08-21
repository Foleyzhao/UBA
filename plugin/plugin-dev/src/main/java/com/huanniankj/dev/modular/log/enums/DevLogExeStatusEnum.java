package com.huanniankj.dev.modular.log.enums;

import lombok.Getter;

/**
 * 日志执行状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevLogExeStatusEnum {

    /**
     * 成功
     */
    SUCCESS("SUCCESS"),

    /**
     * 失败
     */
    FAIL("FAIL");

    private final String value;

    DevLogExeStatusEnum(String value) {
        this.value = value;
    }
}
