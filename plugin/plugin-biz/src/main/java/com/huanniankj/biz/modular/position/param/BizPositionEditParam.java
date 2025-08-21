package com.huanniankj.biz.modular.position.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 岗位编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizPositionEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 机构id
     */
    @Schema(description = "机构id")
    @NotBlank(message = "orgId不能为空")
    private String orgId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 分类
     */
    @Schema(description = "分类")
    @NotBlank(message = "category不能为空")
    private String category;

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
