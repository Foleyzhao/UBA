package com.huanniankj.gen.modular.basic.enums;

import lombok.Getter;

/**
 * 作用类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum GenEffectTypeEnum {

    /**
     * 输入框
     */
    INPUT("INPUT"),

    /**
     * 文本框
     */
    TEXTAREA("TEXTAREA"),

    /**
     * 下拉框
     */
    SELECT("SELECT"),

    /**
     * 单选框
     */
    RADIO("RADIO"),

    /**
     * 复选框
     */
    CHECKBOX("CHECKBOX"),

    /**
     * 日期选择器
     */
    DATEPICKER("DATEPICKER"),

    /**
     * 时间选择器
     */
    TIMEPICKER("TIMEPICKER"),

    /**
     * 数字输入框
     */
    INPUTNUMBER("INPUTNUMBER"),

    /**
     * 滑动数字条
     */
    SLIDER("SLIDER");

    private final String value;

    GenEffectTypeEnum(String value) {
        this.value = value;
    }
}
