package com.huanniankj.uba.modular.config.enums;

import lombok.Getter;

/**
 * 配置分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum ConfigCategoryEnum {

    /**
     * UBA业务定义
     */
    UBA_DEFINE("UBA_DEFINE");

    private final String value;

    ConfigCategoryEnum(String value) {
        this.value = value;
    }
}
