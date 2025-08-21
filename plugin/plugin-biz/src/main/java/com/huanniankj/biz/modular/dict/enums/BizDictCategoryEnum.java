package com.huanniankj.biz.modular.dict.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 业务字典分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizDictCategoryEnum {

    /**
     * 业务
     */
    BIZ("BIZ");

    private final String value;

    BizDictCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = BIZ.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的字典分类：{}", value);
        }
    }
}
