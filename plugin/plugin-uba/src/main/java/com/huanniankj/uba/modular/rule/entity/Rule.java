package com.huanniankj.uba.modular.rule.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("UBA_RULE")
public class Rule extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    private String name;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;

    /**
     * 清洗字段
     */
    @Schema(description = "清洗字段")
    private String field;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String extJson;

}
