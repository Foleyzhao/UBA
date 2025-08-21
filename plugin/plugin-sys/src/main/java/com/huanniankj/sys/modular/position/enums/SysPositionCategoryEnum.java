package com.huanniankj.sys.modular.position.enums;

import lombok.Getter;

/**
 * 职位分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysPositionCategoryEnum {

    /**
     * 高层
     */
    HIGH("HIGH"),

    /**
     * 中层
     */
    MIDDLE("MIDDLE"),

    /**
     * 基层
     */
    LOW("LOW");

    private final String value;

    SysPositionCategoryEnum(String value) {
        this.value = value;
    }

}
