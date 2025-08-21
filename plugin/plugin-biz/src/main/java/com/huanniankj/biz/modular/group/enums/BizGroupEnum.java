package com.huanniankj.biz.modular.group.enums;

import lombok.Getter;

/**
 * 用户组枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizGroupEnum {

    /**
     * 测试
     */
    TEST("TEST");

    private final String value;

    BizGroupEnum(String value) {
        this.value = value;
    }
}
