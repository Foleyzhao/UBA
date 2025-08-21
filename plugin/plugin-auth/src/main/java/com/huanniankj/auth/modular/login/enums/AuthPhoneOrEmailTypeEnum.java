package com.huanniankj.auth.modular.login.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 手机号邮箱类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum AuthPhoneOrEmailTypeEnum {

    /**
     * 手机号
     */
    PHONE("PHONE"),

    /**
     * 邮箱
     */
    EMAIL("EMAIL");

    private final String value;

    AuthPhoneOrEmailTypeEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = PHONE.getValue().equals(value) || EMAIL.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的手机号邮箱类型：{}", value);
        }
    }
}
