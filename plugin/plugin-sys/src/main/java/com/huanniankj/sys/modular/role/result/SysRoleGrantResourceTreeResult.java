package com.huanniankj.sys.modular.role.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色授权资源树结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleGrantResourceTreeResult {

    /**
     * 模块主键
     */
    @Schema(description = "模块主键")
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
     * 模块下菜单集合
     */
    @Schema(description = "模块下菜单集合")
    private List<SysRoleGrantResourceMenuResult> menu;

    /**
     * 授权菜单类
     */
    @Getter
    @Setter
    public static class SysRoleGrantResourceMenuResult {

        /**
         * id
         */
        @Schema(description = "菜单主键")
        private String id;

        /**
         * 父id
         */
        @Schema(description = "菜单id")
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
        private List<SysRoleGrantResourceButtonResult> button;

        /**
         * 授权按钮类
         */
        @Getter
        @Setter
        public static class SysRoleGrantResourceButtonResult {

            /**
             * id
             */
            @Schema(description = "按钮主键")
            private String id;

            /**
             * 标题
             */
            @Schema(description = "按钮标题")
            private String title;
        }
    }
}
