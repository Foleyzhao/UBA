package com.huanniankj.auth.modular.login.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取手机验证码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthGetPhoneValidCodeParam {

    /**
     * 手机号
     */
    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "手机号不能为空")
    private String phone;

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
}
