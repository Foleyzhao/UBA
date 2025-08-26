package com.huanniankj.uba.modular.rule.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则项添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class RuleItemAddParam {

    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    @NotBlank(message = "ruleId不能为空")
    private String ruleId;

    /**
     * 规则项内容
     */
    @Schema(description = "规则项内容")
    @NotBlank(message = "content能为空")
    private String content;

    /**
     * 规则项结果
     */
    @Schema(description = "规则项结果")
    @NotBlank(message = "result不能为空")
    private String result;

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
