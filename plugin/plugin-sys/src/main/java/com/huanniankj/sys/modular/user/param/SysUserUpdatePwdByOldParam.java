package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户通过验证旧密码修改密码参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserUpdatePwdByOldParam {

    /**
     * 旧密码
     */
    @Schema(description = "旧密码")
    @NotBlank(message = "password不能为空")
    private String password;

    /**
     * 新密码
     */
    @Schema(description = "新密码")
    @NotBlank(message = "newPassword不能为空")
    private String newPassword;
}
