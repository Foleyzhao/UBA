package com.huanniankj.sys.modular.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("SYS_RESOURCE")
public class SysMenu extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 别名
     */
    @Schema(description = "别名")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String name;

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
     * 组件
     */
    @Schema(description = "组件")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String component;

    /**
     * 图标
     */
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    @Schema(description = "图标")
    private String icon;

    /**
     * 颜色
     */
    @Schema(description = "颜色")
    private String color;

    /**
     * 是否可见
     */
    @Schema(description = "是否可见")
    private String visible;

    /**
     * 显示布局
     */
    @Schema(description = "显示布局")
    private String displayLayout;

    /**
     * 缓存
     */
    @Schema(description = "缓存")
    private String keepLive;

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
