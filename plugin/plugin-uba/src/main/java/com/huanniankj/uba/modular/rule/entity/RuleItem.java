package com.huanniankj.uba.modular.rule.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则项实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("UBA_RULE_ITEM")
public class RuleItem {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    private String ruleId;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 规则内容
     */
    @Schema(description = "规则项内容")
    private String content;

    /**
     * 规则结果
     */
    @Schema(description = "规则项结果")
    private String result;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;

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
