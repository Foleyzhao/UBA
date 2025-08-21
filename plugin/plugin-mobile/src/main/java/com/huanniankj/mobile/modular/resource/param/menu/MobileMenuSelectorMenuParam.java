package com.huanniankj.mobile.modular.resource.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class MobileMenuSelectorMenuParam {

    /**
     * 模块
     */
    @Schema(description = "模块")
    private String module;
}
