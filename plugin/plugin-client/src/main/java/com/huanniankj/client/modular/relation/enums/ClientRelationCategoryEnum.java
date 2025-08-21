package com.huanniankj.client.modular.relation.enums;

import lombok.Getter;

/**
 * C端关系分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum ClientRelationCategoryEnum {

    /* ====C端用户与其他关系==== */

    /**
     * 测试关系
     */
    USER_TEST("USER_TEST");

    private final String value;

    ClientRelationCategoryEnum(String value) {
        this.value = value;
    }
}
