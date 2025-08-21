package com.huanniankj.dev.modular.config.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevConfigAddParam {

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

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
