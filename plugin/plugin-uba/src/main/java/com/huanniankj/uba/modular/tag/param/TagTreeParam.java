package com.huanniankj.uba.modular.tag.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签树参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class TagTreeParam {

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

}
