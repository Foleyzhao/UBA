package com.huanniankj.sys.modular.user.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户拥有的资源结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserOwnResourceResult {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String id;

    /**
     * 已授权资源信息
     */
    @Schema(description = "已授权资源信息")
    private List<SysUserOwnResource> grantInfoList;

    /**
     * 用户拥有资源类
     */
    @Getter
    @Setter
    public static class SysUserOwnResource {

        /**
         * 菜单id
         */
        @Schema(description = "菜单id")
        private String menuId;

        /**
         * 按钮id集合
         */
        @Schema(description = "按钮id集合")
        private List<String> buttonInfo;
    }

}
