package com.huanniankj.uba.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据处理字典查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DictPageParam {

    /**
     * 当前页
     */
    @Schema(description = "当前页码")
    private Integer current;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer size;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段，字段驼峰名称，如：userName")
    private String sortField;

    /**
     * 排序方式
     */
    @Schema(description = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

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

    /**
     * 字典文字关键词
     */
    @Schema(description = "字典文字关键词")
    private String searchKey;
}
