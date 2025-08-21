package com.huanniankj.auth.modular.login.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮箱验证码登录参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthEmailValidCodeLoginParam {

    /**
     * 邮箱
     */
    @Schema(description = "邮箱", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "验证码不能为空")
    private String validCode;

    /**
     * 验证码请求号
     */
    @Schema(description = "验证码请求号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "验证码请求号不能为空")
    private String validCodeReqNo;

    /**
     * 设备
     */
    @Schema(description = "设备")
    private String device;
}
