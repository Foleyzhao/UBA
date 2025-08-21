package com.huanniankj.auth.modular.monitor.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 会话统计结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthSessionAnalysisResult {

    /**
     * 当前会话总数量
     */
    @Schema(description = "当前会话总数量")
    private String currentSessionTotalCount;

    /**
     * 最大签发令牌数
     */
    @Schema(description = "最大签发令牌数")
    private String maxTokenCount;

    /**
     * 最近1小时会话数
     */
    @Schema(description = "最近1小时会话数")
    private String oneHourNewlyAdded;

    /**
     * BC端会话比例
     */
    @Schema(description = "BC端会话比例")
    private String proportionOfBAndC;
}
