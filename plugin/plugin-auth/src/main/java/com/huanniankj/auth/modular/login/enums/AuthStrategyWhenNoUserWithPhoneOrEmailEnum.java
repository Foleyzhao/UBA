package com.huanniankj.auth.modular.login.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 手机号或邮箱无对应用户时策略
 *
 * @author happynewyear
 */
@Getter
public enum AuthStrategyWhenNoUserWithPhoneOrEmailEnum {

    /**
     * 允许登录（手机或者邮箱存在时）
     */
    ALLOW_LOGIN("ALLOW_LOGIN"),

    /**
     * 不允许登录
     */
    NOT_ALLOW_LOGIN("NOT_ALLOW_LOGIN"),

    /**
     * 自动创建用户
     */
    AUTO_CREATE_USER("AUTO_CREATE_USER");

    private final String value;

    AuthStrategyWhenNoUserWithPhoneOrEmailEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = NOT_ALLOW_LOGIN.getValue().equals(value) || AUTO_CREATE_USER.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的手机号或邮箱无对应用户时策略类型：{}", value);
        }
    }
}
