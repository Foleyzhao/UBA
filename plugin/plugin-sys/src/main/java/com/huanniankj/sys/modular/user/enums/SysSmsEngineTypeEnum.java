package com.huanniankj.sys.modular.user.enums;

import lombok.Getter;

/**
 * 短信发送引擎类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysSmsEngineTypeEnum {

    /**
     * 阿里云
     */
    ALIYUN("ALIYUN"),

    /**
     * 腾讯云
     */
    TENCENT("TENCENT");

    private final String value;

    SysSmsEngineTypeEnum(String value) {
        this.value = value;
    }

}
