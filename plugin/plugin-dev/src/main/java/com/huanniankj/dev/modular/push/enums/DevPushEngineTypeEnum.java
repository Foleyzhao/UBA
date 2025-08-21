package com.huanniankj.dev.modular.push.enums;

import lombok.Getter;

/**
 * 消息推送引擎类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevPushEngineTypeEnum {

    /**
     * 钉钉
     */
    DINGTALK("DINGTALK"),

    /**
     * 飞书
     */
    FEISHU("FEISHU"),

    /**
     * 企业微信
     */
    WORKWECHAT("WORKWECHAT");

    private final String value;

    DevPushEngineTypeEnum(String value) {
        this.value = value;
    }
}
