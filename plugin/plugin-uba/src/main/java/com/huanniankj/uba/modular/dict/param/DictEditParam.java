package com.huanniankj.uba.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据处理字典编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DictEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 父id
     */
    @Schema(description = "父id")
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /**
     * 字典文字
     */
    @Schema(description = "字典文字")
    @NotBlank(message = "dictLabel不能为空")
    private String dictLabel;

    /**
     * 字典值
     */
    @Schema(description = "字典值")
    @NotBlank(message = "dictValue不能为空")
    private String dictValue;

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
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
