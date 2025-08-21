package com.huanniankj.dev.modular.push.enums;

import lombok.Getter;

/**
 * 消息推送通知类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevPushNoticeTypeEnum {

    /**
     * 无通知
     */
    NONE("NONE"),

    /**
     * 通知指定手机号
     */
    PHONE("PHONE"),

    /**
     * 通知所有人
     */
    ALL("ALL");

    private final String value;

    DevPushNoticeTypeEnum(String value) {
        this.value = value;
    }
}
