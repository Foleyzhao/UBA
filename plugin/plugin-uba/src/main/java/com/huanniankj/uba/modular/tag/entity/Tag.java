package com.huanniankj.uba.modular.tag.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("UBA_TAG")
public class Tag extends CommonEntity {

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
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 级别
     */
    @Schema(description = "级别")
    private String level;

    /**
     * 类别
     */
    @Schema(description = "类别")
    private String type;

    /**
     * 标签来源
     */
    @Schema(description = "标签来源")
    private String tagSource;

    /**
     * 标签名称
     */
    @Schema(description = "标签名称")
    private String tagName;

    /**
     * 标签描述
     */
    @Schema(description = "标签描述")
    private String tagDesc;

    /**
     * 编码
     */
    @Schema(description = "编码")
    private String code;

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
