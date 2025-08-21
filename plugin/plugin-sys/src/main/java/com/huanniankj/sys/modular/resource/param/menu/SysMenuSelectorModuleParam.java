package com.huanniankj.sys.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 模块选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysMenuSelectorModuleParam {

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;
}
