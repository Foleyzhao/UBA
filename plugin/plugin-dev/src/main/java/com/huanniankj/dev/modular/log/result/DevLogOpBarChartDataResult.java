package com.huanniankj.dev.modular.log.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志柱状图数据结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevLogOpBarChartDataResult {

    /**
     * 日期
     */
    @Schema(description = "日期")
    private String date;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 数量
     */
    @Schema(description = "数量")
    private Long count;
}
