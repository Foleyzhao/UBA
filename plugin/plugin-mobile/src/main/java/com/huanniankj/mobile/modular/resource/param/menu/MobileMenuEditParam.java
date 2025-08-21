package com.huanniankj.mobile.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileMenuEditParam {

    /**
     * 主键
     */
    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 父ID
     */
    @Schema(description = "父ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title不能为空")
    private String title;

    /**
     * 分类
     */
    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "category不能为空")
    private String category;

    /**
     * 模块
     */
    @Schema(description = "模块", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "module不能为空")
    private String module;

    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "menuType不能为空")
    private String menuType;

    /**
     * 路径
     */
    @Schema(description = "路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "path不能为空")
    private String path;

    /**
     * 图标
     */
    @Schema(description = "图标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "icon不能为空")
    private String icon;

    /**
     * 颜色
     */
    @Schema(description = "颜色", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "color不能为空")
    private String color;

    /**
     * 规则类型
     */
    @Schema(description = "规则类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "regType不能为空")
    private String regType;

    /**
     * 可用状态
     */
    @Schema(description = "可用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "status不能为空")
    private String status;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;
}
