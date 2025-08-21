package com.huanniankj.sys.modular.resource.param.button;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 按钮Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysButtonIdParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
