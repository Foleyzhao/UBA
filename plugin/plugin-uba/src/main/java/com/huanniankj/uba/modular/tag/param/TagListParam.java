package com.huanniankj.uba.modular.tag.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class TagListParam {

    /**
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

}
