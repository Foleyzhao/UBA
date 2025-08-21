package com.huanniankj.dev.modular.push.enums;

import lombok.Getter;

/**
 * 消息推送消息类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevPushMessageTypeEnum {

    /**
     * 文本
     */
    TEXT("TEXT"),

    /**
     * 富文本
     */
    MARKDOWN("MARKDOWN"),

    /**
     * 链接
     */
    LINK("LINK"),

    /**
     * 文章
     */
    NEWS("NEWS");

    private final String value;

    DevPushMessageTypeEnum(String value) {
        this.value = value;
    }
}
