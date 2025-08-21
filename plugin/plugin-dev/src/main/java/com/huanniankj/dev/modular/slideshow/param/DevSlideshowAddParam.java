package com.huanniankj.dev.modular.slideshow.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 轮播图添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevSlideshowAddParam {

    /**
     * 标题
     */
    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title不能为空")
    private String title;

    /**
     * 展示位置
     */
    @Schema(description = "展示位置", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "place不能为空")
    private String place;

    /**
     * 图片
     */
    @Schema(description = "图片")
    private String image;

    /**
     * 路径详情
     */
    @Schema(description = "路径详情", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "pathDetails不能为空")
    private String pathDetails;

    /**
     * 排序
     */
    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

}
