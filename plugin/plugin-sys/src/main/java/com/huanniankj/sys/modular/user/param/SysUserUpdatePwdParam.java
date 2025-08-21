package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户修改密码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserUpdatePwdParam {

    /**
     * 旧密码
     */
    @Schema(description = "旧密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "password不能为空")
    private String password;

    /**
     * 新密码
     */
    @Schema(description = "新密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "newPassword不能为空")
    private String newPassword;
}
