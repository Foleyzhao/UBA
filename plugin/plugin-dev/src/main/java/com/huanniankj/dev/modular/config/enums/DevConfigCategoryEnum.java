package com.huanniankj.dev.modular.config.enums;

import lombok.Getter;

/**
 * 配置分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevConfigCategoryEnum {

    /**
     * 系统基础
     */
    SYS_BASE("SYS_BASE"),

    /**
     * 业务定义
     */
    BIZ_DEFINE("BIZ_DEFINE");

    private final String value;

    DevConfigCategoryEnum(String value) {
        this.value = value;
    }
}
