package com.huanniankj.auth.modular.login.enums;

import lombok.Getter;

/**
 * 登录异常提示语枚举
 *
 * @author happynewyear
 */
@Getter
public enum AuthExceptionEnum {

    /**
     * 验证码不能为空
     */
    VALID_CODE_EMPTY("验证码不能为空"),

    /**
     * 验证码请求号不能为空
     */
    VALID_CODE_REQ_NO_EMPTY("验证码请求号不能为空"),

    /**
     * 验证码过期
     */
    VALID_CODE_EXPIRED("验证码过期"),

    /**
     * 验证码错误
     */
    VALID_CODE_ERROR("验证码错误"),

    /**
     * 账号错误
     */
    ACCOUNT_ERROR("账号错误"),

    /**
     * 账号已停用
     */
    ACCOUNT_DISABLED("账号已停用"),

    /**
     * 密码错误
     */
    PWD_ERROR("密码错误"),

    /**
     * 手机号格式错误
     */
    PHONE_FORMAT_ERROR("手机号格式错误"),

    /**
     * 邮箱格式错误
     */
    EMAIL_FORMAT_ERROR("邮箱格式错误"),

    /**
     * 客户端类型不能为空
     */
    CLIENT_TYPE_EMPTY("客户端类型不能为空"),

    /**
     * 客户端类型错误
     */
    CLIENT_TYPE_ERROR("客户端类型错误"),

    /**
     * 密码解密失败，请检查前端公钥
     */
    PWD_DECRYPT_ERROR("密码解密失败，请检查前端公钥");

    private final String value;

    AuthExceptionEnum(String value) {
        this.value = value;
    }
}
