package com.huanniankj.biz.modular.notice.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 通知公告查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizNoticePageParam {

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
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 发布位置
     */
    @Schema(description = "发布位置")
    private String place;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 创建时间开始
     */
    @Schema(description = "创建时间开始")
    private String startCreateTime;

    /**
     * 创建时间结束
     */
    @Schema(description = "创建时间结束")
    private String endCreateTime;

}
