package com.huanniankj.sys.modular.resource.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 资源分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysResourceCategoryEnum {

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

    SysResourceCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = MODULE.getValue().equals(value) || MENU.getValue().equals(value)
                || BUTTON.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的资源分类：{}", value);
        }
    }
}
