package com.huanniankj.uba.modular.tag.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 标签分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum TagCategoryEnum {

    /**
     * 用户标签
     */
    USER_TAG("USER_TAG");

    private final String value;

    TagCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = USER_TAG.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的标签类别：{}", value);
        }
    }

}
