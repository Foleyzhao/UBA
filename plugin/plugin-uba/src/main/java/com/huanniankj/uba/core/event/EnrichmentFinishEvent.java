package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 访问日志数据增强完成事件
 *
 * @author happynewyear
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class EnrichmentFinishEvent extends PreprocessingFinishEvent {

    /**
     * 数据增强完成
     */
    @JsonProperty("enrichment_finish")
    private Boolean enrichmentFinish = true;

    /**
     * 国家信息
     */
    @JsonProperty("country_info")
    private String countryInfo;

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

    /**
     * 浏览器信息
     */
    @JsonProperty("browser_info")
    private String browserInfo;

    /**
     * 操作系统信息
     */
    @JsonProperty("os_info")
    private String osInfo;

    /**
     * 设备类型信息
     */
    @JsonProperty("device_type_info")
    private String deviceTypeInfo;

}
