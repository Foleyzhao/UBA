package com.huanniankj.sys.modular.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色授权用户参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleGrantUserParam {

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 授权用户信息
     */
    @Schema(description = "授权用户信息")
    @NotNull(message = "grantInfoList不能为空")
    private List<String> grantInfoList;

    /**
     * 是否先清空授权信息
     */
    private Boolean removeFirst = false;
}
