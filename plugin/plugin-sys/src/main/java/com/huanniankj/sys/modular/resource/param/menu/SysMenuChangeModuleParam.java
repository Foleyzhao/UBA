package com.huanniankj.sys.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单更改所属模块参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysMenuChangeModuleParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 模块
     */
    @Schema(description = "module")
    @NotBlank(message = "module不能为空")
    private String module;
}
