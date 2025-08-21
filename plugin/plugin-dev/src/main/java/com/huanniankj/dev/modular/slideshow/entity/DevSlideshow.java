package com.huanniankj.dev.modular.slideshow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import com.huanniankj.common.pojo.CommonEntity;

/**
 * 轮播图实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_SLIDESHOW")
public class DevSlideshow extends CommonEntity {

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
     * 展示位置
     */
    @Schema(description = "展示位置")
    private String place;

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

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
