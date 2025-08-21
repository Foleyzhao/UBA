package com.huanniankj.biz.core.enums;

import lombok.Getter;

/**
 * 系统内置的不可删除的标识枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizBuildInEnum {

    /**
     * 超管用户账号
     */
    BUILD_IN_USER_ACCOUNT("superAdmin", "超管");

    private final String value;

    private final String name;

    BizBuildInEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
