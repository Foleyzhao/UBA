package com.huanniankj.biz.modular.index.result;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 轮播图结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizIndexSlideshowDetailResult {

    /**
     * 主键
     */
    @TableId
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
     * 内容
     */
    @Schema(description = "内容")
    private String content;

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
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private String sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
