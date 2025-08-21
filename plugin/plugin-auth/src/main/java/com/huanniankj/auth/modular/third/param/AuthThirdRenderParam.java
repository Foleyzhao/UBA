package com.huanniankj.auth.modular.third.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 第三方登录授权参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthThirdRenderParam {

    /**
     * 第三方平台标识
     */
    @Schema(description = "第三方平台标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "platform不能为空")
    private String platform;
}
