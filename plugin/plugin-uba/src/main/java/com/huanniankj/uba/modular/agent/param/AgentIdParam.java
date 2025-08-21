package com.huanniankj.uba.modular.agent.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 采集器（agent）Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AgentIdParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
