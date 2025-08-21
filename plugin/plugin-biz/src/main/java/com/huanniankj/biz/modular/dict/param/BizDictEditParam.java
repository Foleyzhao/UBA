package com.huanniankj.biz.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务字典编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizDictEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 字典文字
     */
    @Schema(description = "字典文字")
    @NotBlank(message = "dictLabel不能为空")
    private String dictLabel;

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
