package com.huanniankj.uba.modular.agent.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 采集器（agent）信息上报参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AgentReportParam {

    /**
     * agentId
     */
    @Schema(description = "agentId")
    @NotBlank(message = "agentId不能为空")
    private String agentId;

    /**
     * 配置内容
     */
    @Schema(description = "配置内容")
    @NotBlank(message = "配置内容不能为空")
    private String configName;

    /**
     * 配置值
     */
    @Schema(description = "配置值")
    @NotBlank(message = "配置值不能为空")
    private String configValue;

}
