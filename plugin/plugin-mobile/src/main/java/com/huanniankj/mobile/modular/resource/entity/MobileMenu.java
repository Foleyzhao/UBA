package com.huanniankj.mobile.modular.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端菜单实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("MOBILE_RESOURCE")
public class MobileMenu extends CommonEntity {

    /**
     * 主键
     */
    @TableId
    @Schema(description = "主键")
    private String id;

    /**
     * 父ID
     */
    @Schema(description = "父ID")
    private String parentId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String title;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 模块
     */
    @Schema(description = "模块")
    private String module;

    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型")
    private String menuType;

    /**
     * 路径
     */
    @Schema(description = "路径")
    private String path;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 颜色
     */
    @Schema(description = "颜色")
    private String color;

    /**
     * 规则类型
     */
    @Schema(description = "规则类型")
    private String regType;

    /**
     * 可用状态
     */
    @Schema(description = "可用状态")
    private String status;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String extJson;
}
