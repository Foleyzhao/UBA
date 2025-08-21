package com.huanniankj.mobile.modular.resource.param.module;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端模块添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileModuleAddParam {

    /**
     * 标题
     */
    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title不能为空")
    private String title;

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
     * 排序码
     */
    @Schema(description = "排序码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
