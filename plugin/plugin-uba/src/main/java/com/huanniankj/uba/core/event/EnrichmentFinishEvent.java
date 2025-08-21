package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志数据增强完成事件
 *
 * @author happynewyear
 */
@NoArgsConstructor
@Data
public class EnrichmentFinishEvent extends PreprocessingFinishEvent {

    /**
     * 数据增强完成
     */
    @JsonProperty("enrichment_finish")
    private Boolean enrichmentFinish = true;

    /**
     * 城市信息
     */
    @JsonProperty("city_info")
    private String cityInfo;

    /**
     * 用户代理信息
     */
    @JsonProperty("agent_info")
    private String agentInfo;

}
