package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户通过验证手机号修改密码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserUpdatePwdByPhoneParam {

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @NotBlank(message = "phone不能为空")
    private String phone;

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
