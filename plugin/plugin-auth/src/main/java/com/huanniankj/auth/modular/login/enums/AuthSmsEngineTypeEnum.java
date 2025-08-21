package com.huanniankj.auth.modular.login.enums;

import lombok.Getter;

/**
 * 短信发送引擎类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum AuthSmsEngineTypeEnum {

    /**
     * 阿里云
     */
    ALIYUN("ALIYUN"),

    /**
     * 腾讯云
     */
    TENCENT("TENCENT");

    private final String value;

    AuthSmsEngineTypeEnum(String value) {
        this.value = value;
    }
}
