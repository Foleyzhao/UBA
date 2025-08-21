package com.huanniankj.biz.core.enums;

import lombok.Getter;

/**
 * 业务模块数据类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizDataTypeEnum {

    /**
     * 机构
     */
    ORG("ORG"),

    /**
     * 岗位
     */
    POSITION("POSITION"),

    /**
     * 人员
     */
    USER("USER");

    private final String value;

    BizDataTypeEnum(String value) {
        this.value = value;
    }

}
