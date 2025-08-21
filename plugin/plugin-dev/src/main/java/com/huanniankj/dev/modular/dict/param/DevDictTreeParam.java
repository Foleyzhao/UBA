package com.huanniankj.dev.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典树参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevDictTreeParam {

    /**
     * 字典分类
     */
    @Schema(description = "字典分类")
    private String category;
}
