package com.huanniankj.sys.modular.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色授权资源参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleGrantResourceParam {

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 授权资源信息
     */
    @Valid
    @Schema(description = "授权资源信息")
    @NotNull(message = "grantInfoList不能为空")
    private List<SysRoleGrantResource> grantInfoList;

    /**
     * 角色授权资源类
     */
    @Getter
    @Setter
    public static class SysRoleGrantResource {

        /**
         * 菜单id
         */
        @Schema(description = "菜单id")
        @NotBlank(message = "menuId不能为空")
        private String menuId;

        /**
         * 按钮id集合
         */
        @Schema(description = "按钮id集合")
        @NotNull(message = "buttonInfo不能为空")
        private List<String> buttonInfo;
    }
}
