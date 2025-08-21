package com.huanniankj.uba.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据处理字典列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DictListParam {

    /**
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 字典分类
     */
    @Schema(description = "字典分类")
    private String category;
}
