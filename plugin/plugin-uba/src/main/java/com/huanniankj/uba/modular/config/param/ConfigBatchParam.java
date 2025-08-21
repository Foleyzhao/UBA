package com.huanniankj.uba.modular.config.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置批量更新参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class ConfigBatchParam {

    /**
     * 配置键
     */
    @Schema(description = "配置键")
    @NotBlank(message = "configKey不能为空")
    private String configKey;

    /**
     * 配置值
     */
    @Schema(description = "配置值")
    @NotBlank(message = "configValue不能为空")
    private String configValue;

}
