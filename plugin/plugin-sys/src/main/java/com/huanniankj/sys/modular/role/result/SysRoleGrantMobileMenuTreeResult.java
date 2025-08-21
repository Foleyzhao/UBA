package com.huanniankj.sys.modular.role.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色授权移动端菜单树结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleGrantMobileMenuTreeResult {

    /**
     * 模块id
     */
    @Schema(description = "模块id")
    private String id;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称")
    private String title;

    /**
     * 模块图标
     */
    @Schema(description = "模块图标")
    private String icon;

    /**
     * 模块下移动端菜单集合
     */
    @Schema(description = "模块下移动端菜单集合")
    private List<SysRoleGrantMobileMenuResult> menu;

    /**
     * 授权移动端菜单类
     */
    @Getter
    @Setter
    public static class SysRoleGrantMobileMenuResult {

        /**
         * 菜单id
         */
        @Schema(description = "菜单id")
        private String id;

        /**
         * 父id
         */
        @Schema(description = "父id")
        private String parentId;

        /**
         * 父名称
         */
        @Schema(description = "菜单名称")
        private String parentName;

        /**
         * 标题
         */
        @Schema(description = "菜单标题")
        private String title;

        /**
         * 模块
         */
        @Schema(description = "菜单模块")
        private String module;

        /**
         * 菜单下按钮集合
         */
        @Schema(description = "菜单下按钮集合")
        private List<SysRoleGrantMobileButtonResult> button;

        /**
         * 授权按钮类
         */
        @Getter
        @Setter
        public static class SysRoleGrantMobileButtonResult {

            /**
             * 按钮id
             */
            @Schema(description = "按钮id")
            private String id;

            /**
             * 标题
             */
            @Schema(description = "按钮标题")
            private String title;
        }
    }
}
