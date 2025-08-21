package com.huanniankj.biz.modular.group.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户组编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizGroupEditParam {

    /**
     * 主键
     */
    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 名称
     */
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 排序码
     */
    @Schema(description = "排序码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;
}
