package com.huanniankj.client.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户通过验证邮箱修改密码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class ClientUserUpdatePwdByEmailParam {

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    @NotBlank(message = "email不能为空")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    @NotBlank(message = "validCode不能为空")
    private String validCode;

    /**
     * 验证码请求号
     */
    @Schema(description = "验证码请求号")
    @NotBlank(message = "validCodeReqNo不能为空")
    private String validCodeReqNo;

    /**
     * 新密码
     */
    @Schema(description = "新密码")
    @NotBlank(message = "newPassword不能为空")
    private String newPassword;
}
