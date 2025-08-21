package com.huanniankj.uba.modular.tag.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 标签来源枚举
 *
 * @author happynewyear
 */
@Getter
public enum TagSourceEnum {

    /**
     * 静态型
     */
    STATIC("STATIC"),

    /**
     * 计算型
     */
    CALCULATION("CALCULATION"),

    /**
     * 统计型
     */
    STATISTICAL("STATISTICAL"),

    /**
     * 规则型
     */
    RULE("RULE"),

    /**
     * 算法型
     */
    ALGORITHMIC("ALGORITHMIC"),

    /**
     * 预测型
     */
    PREDICTIVE("PREDICTIVE");

    private final String value;

    TagSourceEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = STATIC.getValue().equals(value) || CALCULATION.getValue().equals(value)
                || STATISTICAL.getValue().equals(value) || RULE.getValue().equals(value)
                || ALGORITHMIC.getValue().equals(value) || PREDICTIVE.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的标签来源：{}", value);
        }
    }

}
