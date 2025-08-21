package com.huanniankj.mobile.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单类别选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileMenuSelectorModuleParam {

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;
}
