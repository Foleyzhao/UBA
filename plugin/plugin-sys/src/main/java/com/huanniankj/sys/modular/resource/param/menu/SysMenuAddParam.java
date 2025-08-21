package com.huanniankj.sys.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysMenuAddParam {

    /**
     * 父id
     */
    @Schema(description = "父id")
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @NotBlank(message = "title不能为空")
    private String title;

    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型")
    @NotBlank(message = "menuType不能为空")
    private String menuType;

    /**
     * 模块
     */
    @Schema(description = "模块")
    @NotBlank(message = "module不能为空")
    private String module;

    /**
     * 路径
     */
    @Schema(description = "路径")
    @NotBlank(message = "path不能为空")
    private String path;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 别名
     */
    @Schema(description = "别名")
    private String name;

    /**
     * 组件
     */
    @Schema(description = "组件")
    private String component;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见")
    private String visible;

    /**
     * 显示布局
     */
    @Schema(description = "显示布局")
    private String displayLayout;

    /**
     * 缓存
     */
    @Schema(description = "缓存")
    private String keepLive;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
