package com.huanniankj.sys.modular.org.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 组织查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysOrgPageParam {

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
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;

}
