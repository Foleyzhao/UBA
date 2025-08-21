package com.huanniankj.biz.modular.notice.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 通知公告状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizNoticeStatusEnum {

    /**
     * 正常
     */
    ENABLE("ENABLE"),

    /**
     * 禁用
     */
    DISABLE("DISABLE");

    private final String value;

    BizNoticeStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLE.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的状态：{}", value);
        }
    }
}
