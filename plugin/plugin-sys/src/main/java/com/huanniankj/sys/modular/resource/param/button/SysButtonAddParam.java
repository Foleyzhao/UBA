package com.huanniankj.sys.modular.resource.param.button;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 按钮添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysButtonAddParam {

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
     * 编码
     */
    @Schema(description = "编码")
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 扩展JSON
     */
    @Schema(description = "扩展JSON")
    private String extJson;
}
