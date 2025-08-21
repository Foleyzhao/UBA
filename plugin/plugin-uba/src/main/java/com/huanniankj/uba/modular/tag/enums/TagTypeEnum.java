package com.huanniankj.uba.modular.tag.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 标签类别枚举
 *
 * @author happynewyear
 */
@Getter
public enum TagTypeEnum {

    /**
     * 静态标签
     */
    STATIC_TAGS("STATIC_TAG"),

    /**
     * 动态标签
     */
    DYNAMIC_TAGS("DYNAMIC_TAG"),

    /**
     * 组合标签
     */
    COMBINATION_TAGS("COMBINATION_TAG");

    private final String value;

    TagTypeEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = STATIC_TAGS.getValue().equals(value) || DYNAMIC_TAGS.getValue().equals(value)
                || COMBINATION_TAGS.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的标签分类：{}", value);
        }
    }

}
