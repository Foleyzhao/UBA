package com.huanniankj.sys.modular.role.enums;

import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

/**
 * 给角色授权权限对应的数据范围枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysRoleDataScopeCategoryEnum {

    /**
     * 全部
     */
    SCOPE_ALL("SCOPE_ALL"),

    /**
     * 仅自己
     */
    SCOPE_SELF("SCOPE_SELF"),

    /**
     * 仅所属组织
     */
    SCOPE_ORG("SCOPE_ORG"),

    /**
     * 所属组织及以下
     */
    SCOPE_ORG_CHILD("SCOPE_ORG_CHILD"),

    /**
     * 自定义组织
     */
    SCOPE_ORG_DEFINE("SCOPE_ORG_DEFINE");

    private final String value;

    SysRoleDataScopeCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = SCOPE_ALL.getValue().equals(value) || SCOPE_SELF.getValue().equals(value) ||
                SCOPE_ORG.getValue().equals(value) || SCOPE_ORG_CHILD.getValue().equals(value) ||
                SCOPE_ORG_DEFINE.getValue().equals(value);
        if (!flag) {
            throw new CommonException("不支持的数据范围：{}", value);
        }
    }
}
