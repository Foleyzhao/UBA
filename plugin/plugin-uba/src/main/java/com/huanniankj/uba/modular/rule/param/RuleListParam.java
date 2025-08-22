package com.huanniankj.uba.modular.rule.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据清洗规则列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class RuleListParam {

    /**
     * 字典分类
     */
    @Schema(description = "分类")
    private String category;

}
