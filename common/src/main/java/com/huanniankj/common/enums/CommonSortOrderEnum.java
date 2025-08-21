package com.huanniankj.common.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 通用排序方式枚举
 *
 * @author happynewyear
 */
@Getter
public enum CommonSortOrderEnum {

    /**
     * 升序
     */
    ASC("ASCEND"),

    /**
     * 降序
     */
    DESC("DESCEND");

    private final String value;

    CommonSortOrderEnum(String value) {
        this.value = value.toLowerCase();
    }

    public static void validate(String value) {
        boolean flag = ASC.getValue().toLowerCase().equals(value) || DESC.getValue().toLowerCase().equals(value);
        if (!flag) {
            throw new CommonException("不支持该排序方式：{}", value);
        }
    }
}
