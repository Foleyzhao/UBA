package com.huanniankj.uba.modular.rule.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class RuleAddParam {

    /**
     * 分类
     */
    @Schema(description = "分类")
    @NotBlank(message = "category不能为空")
    private String category;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 清洗字段
     */
    @Schema(description = "清洗字段")
    @NotBlank(message = "field不能为空")
    private String field;

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
