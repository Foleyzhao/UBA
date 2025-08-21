package com.huanniankj.biz.modular.index.result;

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
public class BizIndexSlideshowListResult {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 图片
     */
    @Schema(description = "图片")
    private String image;

    /**
     * 路径详情
     */
    @Schema(description = "路径详情")
    private String pathDetails;
}
