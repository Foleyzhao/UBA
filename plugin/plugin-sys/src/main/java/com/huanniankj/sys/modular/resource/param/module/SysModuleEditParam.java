package com.huanniankj.sys.modular.resource.param.module;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 模块编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysModuleEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @NotBlank(message = "title不能为空")
    private String title;

    /**
     * 图标
     */
    @Schema(description = "图标")
    @NotBlank(message = "icon不能为空")
    private String icon;

    /**
     * 颜色
     */
    @Schema(description = "颜色")
    @NotBlank(message = "color不能为空")
    private String color;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
