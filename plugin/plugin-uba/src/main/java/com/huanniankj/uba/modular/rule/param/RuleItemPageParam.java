package com.huanniankj.uba.modular.rule.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则项查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class RuleItemPageParam {

    /**
     * 当前页
     */
    @Schema(description = "当前页码")
    private Integer current;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer size;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段，字段驼峰名称，如：userName")
    private String sortField;

    /**
     * 排序方式
     */
    @Schema(description = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    private String ruleId;

    /**
     * 关键词
     */
    @Schema(description = "关键词")
    private String searchKey;

}
