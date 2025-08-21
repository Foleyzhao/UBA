package com.huanniankj.sys.modular.group.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户组查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysGroupPageParam {

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
     * 关键词
     */
    @Schema(description = "关键词")
    private String searchKey;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

}
