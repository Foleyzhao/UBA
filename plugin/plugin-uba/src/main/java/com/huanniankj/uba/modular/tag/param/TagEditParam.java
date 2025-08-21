package com.huanniankj.uba.modular.tag.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class TagEditParam {

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
     * 分类
     */
    @Schema(description = "分类")
    @NotBlank(message = "category不能为空")
    private String category;

    /**
     * 级别
     */
    @Schema(description = "级别")
    @NotBlank(message = "level不能为空")
    private String level;

    /**
     * 类别
     */
    @Schema(description = "类别")
    @NotBlank(message = "type不能为空")
    private String type;

    /**
     * 标签来源
     */
    @Schema(description = "标签来源")
    @NotBlank(message = "tagSource不能为空")
    private String tagSource;

    /**
     * 标签名称
     */
    @Schema(description = "标签名称")
    @NotBlank(message = "tagName不能为空")
    private String tagName;

    /**
     * 标签描述
     */
    @Schema(description = "标签描述")
    @NotBlank(message = "tagDesc不能为空")
    private String tagDesc;

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
