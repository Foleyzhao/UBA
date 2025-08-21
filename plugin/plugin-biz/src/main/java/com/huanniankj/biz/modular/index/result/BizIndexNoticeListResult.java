package com.huanniankj.biz.modular.index.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 轮播图结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizIndexNoticeListResult {

    /**
     * 主键
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 封面图
     */
    @Schema(description = "封面图")
    private String image;

    /**
     * 摘要
     */
    @Schema(description = "摘要")
    private String digest;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String type;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String sortCode;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
}
