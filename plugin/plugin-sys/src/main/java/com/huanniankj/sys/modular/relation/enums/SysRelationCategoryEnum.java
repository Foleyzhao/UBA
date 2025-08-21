package com.huanniankj.sys.modular.relation.enums;

import lombok.Getter;

/**
 * 关系分类枚举
 *
 * @author happynewyear
 */
@Getter
public enum SysRelationCategoryEnum {

    /**
     * 用户工作台数据
     */
    SYS_USER_WORKBENCH_DATA("SYS_USER_WORKBENCH_DATA"),

    /**
     * 用户日程数据
     */
    SYS_USER_SCHEDULE_DATA("SYS_USER_SCHEDULE_DATA"),

    /**
     * 用户拥有资源
     */
    SYS_USER_HAS_RESOURCE("SYS_USER_HAS_RESOURCE"),

    /**
     * 用户拥有权限
     */
    SYS_USER_HAS_PERMISSION("SYS_USER_HAS_PERMISSION"),

    /**
     * 用户拥有角色
     */
    SYS_USER_HAS_ROLE("SYS_USER_HAS_ROLE"),

    /**
     * 角色拥有资源
     */
    SYS_ROLE_HAS_RESOURCE("SYS_ROLE_HAS_RESOURCE"),

    /**
     * 角色拥有移动端菜单
     */
    SYS_ROLE_HAS_MOBILE_MENU("SYS_ROLE_HAS_MOBILE_MENU"),

    /**
     * 角色拥有权限
     */
    SYS_ROLE_HAS_PERMISSION("SYS_ROLE_HAS_PERMISSION"),

    /**
     * 用户拥有用户组
     */
    SYS_USER_HAS_GROUP("SYS_USER_HAS_GROUP");

    private final String value;

    SysRelationCategoryEnum(String value) {
        this.value = value;
    }
}
