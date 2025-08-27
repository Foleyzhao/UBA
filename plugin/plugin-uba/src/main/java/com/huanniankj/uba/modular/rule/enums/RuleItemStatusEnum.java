package com.huanniankj.uba.modular.rule.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 数据清洗规则项状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum RuleItemStatusEnum {

    /**
     * 启用
     */
    ENABLE("ENABLE"),

    /**
     * 禁用
     */
    DISABLE("DISABLE");

    private final String value;

    RuleItemStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLE.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的数据清洗规则项状态：{}", value);
        }
    }

}
