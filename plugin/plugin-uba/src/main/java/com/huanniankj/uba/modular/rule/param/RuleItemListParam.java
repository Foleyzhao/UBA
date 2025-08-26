package com.huanniankj.uba.modular.rule.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则项列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class RuleItemListParam {

    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    private String ruleId;

}
