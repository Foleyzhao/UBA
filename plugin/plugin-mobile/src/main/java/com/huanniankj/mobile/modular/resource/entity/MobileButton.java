package com.huanniankj.mobile.modular.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 移动端按钮实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("MOBILE_RESOURCE")
public class MobileButton extends CommonEntity {

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
