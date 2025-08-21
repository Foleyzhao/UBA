package com.huanniankj.dev.modular.config.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevConfigListParam {

    /**
     * 配置分类
     */
    @Schema(description = "配置分类")
    private String category;
}
