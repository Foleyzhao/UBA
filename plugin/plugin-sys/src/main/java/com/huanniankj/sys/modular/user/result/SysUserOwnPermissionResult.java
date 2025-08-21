package com.huanniankj.sys.modular.user.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户拥有的权限结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserOwnPermissionResult {

    /**
     * 用户id
     */
    @Schema(description = "角色id")
    private String id;

    /**
     * 已授权权限信息
     */
    @Schema(description = "已授权权限信息")
    private List<SysUserOwnPermission> grantInfoList;

    /**
     * 用户拥有资源类
     */
    @Getter
    @Setter
    public static class SysUserOwnPermission {

        /**
         * 菜单id
         */
        @Schema(description = "接口地址")
        private String apiUrl;

        /**
         * 数据范围分类
         */
        @Schema(description = "数据范围分类")
        private String scopeCategory;

        /**
         * 自定义范围组织id集合
         */
        @Schema(description = "自定义范围组织id集合")
        private List<String> scopeDefineOrgIdList;

    }

}
