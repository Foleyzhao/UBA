package com.huanniankj.sys.core.enums;

import lombok.Getter;

/**
 * 密码复杂度类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysPasswordComplexityEnum {

    /**
     * 无限制
     */
    REG0("REG0", "无限制"),

    /**
     * 必须包含数字和字母
     */
    REG1("REG1", "必须包含数字和字母"),

    /**
     * 必须包含数字和大写字母
     */
    REG2("REG2", "必须包含数字和大写字母"),

    /**
     * 必须包含数字、大写字母、小写字母和特殊字符
     */
    REG3("REG3", "必须包含数字、大写字母、小写字母和特殊字符"),

    /**
     * 至少包含数字、字母和特殊字符中的两种
     */
    REG4("REG4", "至少包含数字、字母和特殊字符中的两种"),

    /**
     * 至少包含数字、大写字母、小写字母和特殊字符的三种
     */
    REG5("REG5", "至少包含数字、大写字母、小写字母和特殊字符的三种");

    private final String value;

    private final String message;

    SysPasswordComplexityEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }
}
