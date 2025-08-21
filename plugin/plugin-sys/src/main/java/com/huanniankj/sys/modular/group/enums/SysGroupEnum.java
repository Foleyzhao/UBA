package com.huanniankj.sys.modular.group.enums;

import lombok.Getter;

/**
 * 用户组枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysGroupEnum {

    /**
     * 测试
     */
    TEST("TEST");

    private final String value;

    SysGroupEnum(String value) {
        this.value = value;
    }

}
