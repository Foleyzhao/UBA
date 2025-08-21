package com.huanniankj.dev.modular.message.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 站内信查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevMessagePageParam {

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
     * 站内信分类
     */
    @Schema(description = "站内信分类")
    private String category;

    /**
     * 主题关键词
     */
    @Schema(description = "主题关键词")
    private String searchKey;

    /**
     * 接收用户id
     */
    @Schema(description = "接收用户id")
    private String receiveUserId;
}
