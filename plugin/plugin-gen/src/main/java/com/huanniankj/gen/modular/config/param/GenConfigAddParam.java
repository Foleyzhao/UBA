package com.huanniankj.gen.modular.config.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成详细配置添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class GenConfigAddParam {

    /**
     * 基础ID
     */
    @Schema(description = "基础ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "basicId不能为空")
    private String basicId;

    /**
     * 是否是主键
     */
    @Schema(description = "是否是主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "isTableKey不能为空")
    private String isTableKey;

    /**
     * 字段
     */
    @Schema(description = "字段", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "fieldName不能为空")
    private String fieldName;

    /**
     * 注释
     */
    @Schema(description = "注释", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "fieldRemark不能为空")
    private String fieldRemark;

    /**
     * 类型
     */
    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "fieldType不能为空")
    private String fieldType;

    /**
     * 实体类型
     */
    @Schema(description = "实体类型")
    private String fieldJavaType;

    /**
     * 作用类型
     */
    @Schema(description = "作用类型")
    private String effectType;

    /**
     * 字典
     */
    @Schema(description = "字典")
    private String dictTypeCode;

    /**
     * 列表显示
     */
    @Schema(description = "列表显示")
    private String whetherTable;

    /**
     * 列省略
     */
    @Schema(description = "列省略")
    private String whetherRetract;

    /**
     * 增改
     */
    @Schema(description = "增改")
    private String whetherAddUpdate;

    /**
     * 必填
     */
    @Schema(description = "必填")
    private String whetherRequired;

    /**
     * 唯一
     */
    @Schema(description = "唯一")
    private String whetherUnique;

    /**
     * 查询
     */
    @Schema(description = "查询")
    private String queryWhether;

    /**
     * 查询方式
     */
    @Schema(description = "查询方式")
    private String queryType;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortCode;
}
