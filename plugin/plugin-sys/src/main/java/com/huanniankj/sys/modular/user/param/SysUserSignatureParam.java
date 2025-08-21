package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户修改签名图片参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserSignatureParam {

    /**
     * 签名图片base64编码
     */
    @Schema(description = "signature")
    @NotBlank(message = "signature签名图片不能为空")
    private String signature;
}
