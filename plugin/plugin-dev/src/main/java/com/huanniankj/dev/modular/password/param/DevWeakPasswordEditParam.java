package com.huanniankj.dev.modular.password.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 弱密码库编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevWeakPasswordEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "password不能为空")
    private String password;
}
