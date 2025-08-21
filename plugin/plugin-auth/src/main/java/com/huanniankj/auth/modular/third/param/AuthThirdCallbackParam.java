package com.huanniankj.auth.modular.third.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 第三方登录回调参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthThirdCallbackParam {

    /**
     * 第三方平台标识
     */
    @Schema(description = "第三方平台标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "platform不能为空")
    private String platform;

    /**
     * 第三方回调code
     */
    @Schema(description = "第三方回调code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "code不能为空")
    private String code;

    /**
     * 第三方回调state
     */
    @Schema(description = "第三方回调state", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "state不能为空")
    private String state;
}
