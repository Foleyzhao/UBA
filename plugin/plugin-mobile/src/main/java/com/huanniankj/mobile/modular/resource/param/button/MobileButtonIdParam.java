package com.huanniankj.mobile.modular.resource.param.button;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端按钮Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileButtonIdParam {

    /**
     * id
     */
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;
}
