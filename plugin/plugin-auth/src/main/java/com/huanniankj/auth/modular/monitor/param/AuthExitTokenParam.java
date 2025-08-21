package com.huanniankj.auth.modular.monitor.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Token退出参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthExitTokenParam {

    /**
     * token值
     */
    @Schema(description = "token值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "tokenValue不能为空")
    private String tokenValue;
}
