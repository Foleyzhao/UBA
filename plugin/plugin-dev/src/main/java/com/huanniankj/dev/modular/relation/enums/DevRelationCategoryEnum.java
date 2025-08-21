package com.huanniankj.dev.modular.relation.enums;

import lombok.Getter;

/**
 * 关系分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum DevRelationCategoryEnum {

    /* ====文件与业务关系==== */

    /**
     * 文件与业务默认关联关系，后续有多种类型关联，可扩展
     */
    FILE_TO_BIZ_DEFAULT("FILE_TO_BIZ_DEFAULT"),

    /* ====站内信与用户关系==== */

    /**
     * 站内信与接收用户
     */
    MSG_TO_USER("MSG_TO_USER");

    private final String value;

    DevRelationCategoryEnum(String value) {
        this.value = value;
    }
}
