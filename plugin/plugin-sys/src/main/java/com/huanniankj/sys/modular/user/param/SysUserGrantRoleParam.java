package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户授权角色参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserGrantRoleParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 角色id集合
     */
    @Schema(description = "角色id集合")
    @NotNull(message = "roleIdList不能为空")
    private List<String> roleIdList;
}
