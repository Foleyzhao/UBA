package com.huanniankj.uba.modular.tag.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 标签级别枚举
 *
 * @author happynewyear
 */
@Getter
public enum TagLevelEnum {

    /**
     * 一级
     */
    LEVEL_ONE("LEVEL_ONE"),

    /**
     * 二级
     */
    LEVEL_TWO("LEVEL_TWO"),

    /**
     * 三级
     */
    LEVEL_THREE("LEVEL_THREE");

    private final String value;

    TagLevelEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = LEVEL_ONE.getValue().equals(value) || LEVEL_TWO.getValue().equals(value)
                || LEVEL_THREE.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的标签级别：{}", value);
        }
    }

}
