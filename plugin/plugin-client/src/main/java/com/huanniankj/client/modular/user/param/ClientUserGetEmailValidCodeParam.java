package com.huanniankj.client.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取邮箱验证码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class ClientUserGetEmailValidCodeParam {

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String validCode;

    /**
     * 验证码请求号
     */
    @Schema(description = "验证码请求号")
    @NotBlank(message = "验证码请求号不能为空")
    private String validCodeReqNo;
}
