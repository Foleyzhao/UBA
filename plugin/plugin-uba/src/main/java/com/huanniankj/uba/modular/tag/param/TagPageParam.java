package com.huanniankj.uba.modular.tag.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class TagPageParam {

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
    @Schema(description = "排序字段，字段驼峰名称，如：tagName")
    private String sortField;

    /**
     * 排序方式
     */
    @Schema(description = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 标签文字关键词
     */
    @Schema(description = "标签文字关键词")
    private String searchKey;

}
