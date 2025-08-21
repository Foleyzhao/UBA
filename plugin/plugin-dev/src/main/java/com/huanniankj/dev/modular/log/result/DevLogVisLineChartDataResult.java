package com.huanniankj.dev.modular.log.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 访问日志折线图数据结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevLogVisLineChartDataResult {

    /**
     * 日期
     */
    @Schema(description = "日期")
    private String date;

    /**
     * 登录数量
     */
    @Schema(description = "登录数量")
    private Long loginCount;

    /**
     * 登出数量
     */
    @Schema(description = "登出数量")
    private Long logoutCount;
}
