package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户授权权限参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserGrantPermissionParam {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 授权权限信息
     */
    @Valid
    @Schema(description = "授权权限信息")
    @NotNull(message = "grantInfoList不能为空")
    private List<SysUserGrantPermission> grantInfoList;

    /**
     * 用户授权权限类
     */
    @Getter
    @Setter
    public static class SysUserGrantPermission {

        /**
         * 接口地址
         */
        @Schema(description = "接口地址")
        @NotBlank(message = "apiUrl不能为空")
        private String apiUrl;

        /**
         * 数据范围分类
         */
        @Schema(description = "数据范围分类")
        @NotBlank(message = "scopeCategory不能为空")
        private String scopeCategory;

        /**
         * 自定义范围组织id集合
         */
        @Schema(description = "自定义范围组织id集合")
        @NotNull(message = "scopeDefineOrgIdList不能为空")
        private List<String> scopeDefineOrgIdList;
    }
}
