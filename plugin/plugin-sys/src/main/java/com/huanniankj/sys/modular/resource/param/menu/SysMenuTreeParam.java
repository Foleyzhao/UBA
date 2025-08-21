package com.huanniankj.sys.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单树查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysMenuTreeParam {

    /**
     * 模块
     */
    @Schema(description = "模块")
    private String module;

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;
}
