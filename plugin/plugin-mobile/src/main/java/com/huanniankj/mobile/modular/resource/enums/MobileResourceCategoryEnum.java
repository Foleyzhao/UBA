package com.huanniankj.mobile.modular.resource.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 移动端资源分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum MobileResourceCategoryEnum {

    /**
     * 模块
     */
    MODULE("MODULE"),

    /**
     * 菜单
     */
    MENU("MENU"),

    /**
     * 按钮
     */
    BUTTON("BUTTON");

    private final String value;

    MobileResourceCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = MODULE.getValue().equals(value) || MENU.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的资源分类：{}", value);
        }
    }
}
