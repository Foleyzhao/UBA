package com.huanniankj.biz.modular.org.enums;

import lombok.Getter;

/**
 * 机构来源类型枚举
 *
 * @author happynewyear
 */
@Getter
public enum BizOrgSourceFromTypeEnum {

    /**
     * 系统自建
     */
    SYSTEM_ADD("SYSTEM_ADD"),

    /**
     * 身份源
     */
    ID_SOURCE("ID_SOURCE");

    private final String value;

    BizOrgSourceFromTypeEnum(String value) {
        this.value = value;
    }
}
