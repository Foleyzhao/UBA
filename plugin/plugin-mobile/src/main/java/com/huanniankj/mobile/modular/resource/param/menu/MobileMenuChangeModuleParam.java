package com.huanniankj.mobile.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单更改所属模块参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileMenuChangeModuleParam {

    /**
     * id
     */
    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 模块
     */
    @Schema(description = "module", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "module不能为空")
    private String module;
}
