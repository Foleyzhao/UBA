package com.huanniankj.uba.modular.tag.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 标签数据类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum TagDataTypeEnum {

    STRING("STRING"),

    NUMBER("NUMBER"),

    BOOLEAN("BOOLEAN"),

    DATE("DATE"),

    DATETIME("DATETIME");

    private final String value;

    TagDataTypeEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = STRING.getValue().equals(value) || NUMBER.getValue().equals(value)
                || BOOLEAN.getValue().equals(value) || DATE.getValue().equals(value)
                || DATETIME.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的标签数据类型：{}", value);
        }
    }

}
