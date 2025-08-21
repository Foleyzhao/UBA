package com.huanniankj.dev.modular.dict.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 字典分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevDictCategoryEnum {

    /**
     * 框架
     */
    FRM("FRM"),

    /**
     * 业务
     */
    BIZ("BIZ");

    private final String value;

    DevDictCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = FRM.getValue().equals(value) || BIZ.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的字典分类：{}", value);
        }
    }
}
